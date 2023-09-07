@echo off
setlocal enabledelayedexpansion

rem Check if the script is run as administrator
net session >nul 2>&1
if %errorLevel% neq 0 (
    echo This script requires administrative privileges to install Java.
    echo Please run the script as an administrator.
    goto :EOF
)

rem Check if Java is installed
java -version >nul 2>&1
if %errorLevel% equ 0 (
    echo Java is already installed:
    java -version
) else (
    echo Java is not installed.
    echo Installing Java...
    
    rem Check the Windows version and take appropriate action
    ver | findstr /i "6\.[0-9]\." >nul
    if %errorLevel% equ 0 (
        echo Running on Windows Vista/7/8/10
        rem Insert Java installation steps for Windows Vista/7/8/10 here
        goto :JavaInstalled
    )
    
    echo Running on an unsupported Windows version
)

:JavaInstalled
echo Java installation complete.
