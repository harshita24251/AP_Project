@echo off
REM ===================================================
REM Run ERP Java Program (auto-compile + silent run)
REM ===================================================

REM Change directory to where this batch file is located
cd /d "%~dp0"

REM ===================================================
REM Compile only if newer source files exist
REM ===================================================
set SRC_DIR=src
set BIN_DIR=bin

REM Check if BIN_DIR exists; if not, create it
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

echo Checking for changes...
for /r "%SRC_DIR%" %%f in (*.java) do (
    set "SRC_FILE=%%f"
    set "CLASS_FILE=%BIN_DIR%%%~pnxf"
    set "CLASS_FILE=%CLASS_FILE:.java=.class%"
    if not exist "%CLASS_FILE%" (
        set NEEDS_COMPILE=true
    ) else (
        for %%A in ("%%f") do for %%B in ("%CLASS_FILE%") do (
            if %%~tA GTR %%~tB set NEEDS_COMPILE=true
        )
    )
)

if defined NEEDS_COMPILE (
    echo Compiling updated Java files...
    javac -cp "src;.;lib\*;lib\assets" -d bin src\edu\univ\erp\ui\login\tmp.java
    if %errorlevel% neq 0 (
        echo.
        echo ❌ Compilation failed!
        pause
        exit /b %errorlevel%
    )
    echo ✅ Compilation successful.
) else (
    echo No changes detected. Skipping compilation.
)

REM ===================================================
REM Run silently using javaw (no console window)
REM ===================================================
start "" javaw -cp "bin;lib/*;lib/assets" edu.univ.erp.ui.login.tmp

exit /b 0
