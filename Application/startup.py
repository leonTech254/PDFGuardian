#!/usr/bin/env python
import os
import platform
import subprocess

def check_root():
    if os.geteuid() != 0:
        print("This script requires root privileges to install Java.")
        print("Please run the script with 'sudo'.")
        exit(1)

def check_java():
    try:
        check_root()
        subprocess.check_output(["java", "-version"], stderr=subprocess.STDOUT)
        print("Java is already installed:")
        subprocess.call(["java", "-version"])
    except subprocess.CalledProcessError:
        print("Java is not installed.")
        if platform.system() == "Linux":
            check_root()
            print("Running on Linux/Unix")
            subprocess.call(["sudo", "apt-get", "update"])
            subprocess.call(["sudo", "apt-get", "install", "-y", "openjdk-11-jre"])
        elif platform.system() == "Darwin":
            print("Running on macOS")
            subprocess.call(["brew", "install", "openjdk@11"])
        elif platform.system().startswith("MINGW"):
            print("Running on Windows (with WSL)")
            print("Please install Java manually")
        else:
            print("Running on an unsupported platform")
            return

        if os.system("java -version") == 0:
            print("Java installation successful. Please continue.")
        else:
            print("Java installation failed.")

if __name__ == "__main__":
    check_java()
