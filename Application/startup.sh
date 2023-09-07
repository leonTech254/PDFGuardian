#!/bin/bash
check_root() {
    if [ "$(whoami)" != "root" ]; then
        echo "This script requires root privileges to install Java."
        echo "Please run the script with 'sudo'."
        exit 1
    fi
}
check_java() {
    if command -v java &>/dev/null; then
     check_root
        echo "Java is already installed:"
        java -version
        
    else
        echo "Java is not installed."
        if [ "$(uname)" == "Linux" ]; then
            check_root
            echo "Running on Linux/Unix"
            sudo apt-get update
            sudo apt-get install -y openjdk-11-jre
        elif [ "$(uname)" == "Darwin" ]; then
            echo "Running on macOS"
            brew install openjdk@11
        elif [ "$(expr substr $(uname -s) 1 5)" == "MINGW" ]; then
            echo "Running on Windows (with WSL)"
            echo "Please install java manually"
        else
            echo "Running on an unsupported platform"
        fi
        if [ $? -eq 0 ]; then
            echo "Java installation successful. Please continue."
        else
            echo "Java installation failed."
        fi
    fi
}

check_java
