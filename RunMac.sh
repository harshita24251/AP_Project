#!/bin/bash
# ====================================================
# Run ERP Java Program (auto-compile + run)
# Compatible with macOS and Linux
# ====================================================

# Go to the directory where this script is located
cd "$(dirname "$0")"

SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="edu.univ.erp.ui.login.tmp"

# Create bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

echo "Checking for changes..."
NEEDS_COMPILE=false

# Check if any .java file is newer than its corresponding .class
while IFS= read -r -d '' src; do
  class="${src/$SRC_DIR/$BIN_DIR}"
  class="${class%.java}.class"
  if [[ ! -f "$class" || "$src" -nt "$class" ]]; then
    NEEDS_COMPILE=true
    break
  fi
done < <(find "$SRC_DIR" -name "*.java" -print0)

if [ "$NEEDS_COMPILE" = true ]; then
  echo "Compiling updated Java files..."
  javac -cp "src:.:lib/*:lib/assets" -d "$BIN_DIR" "$SRC_DIR/edu/univ/erp/ui/login/tmp.java"
  if [ $? -ne 0 ]; then
    echo ""
    echo "❌ Compilation failed!"
    exit 1
  fi
  echo "✅ Compilation successful."
else
  echo "No changes detected. Skipping compilation."
fi

echo ""
echo "🚀 Launching ERP Application..."
nohup java -cp "bin:lib/*:lib/assets" "$MAIN_CLASS" >/dev/null 2>&1 &
disown
