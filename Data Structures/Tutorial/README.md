# 🛠️ C/C++ Setup Guide (MinGW Installation on Windows)

This guide explains how to install **MinGW (Minimalist GNU for Windows)** and configure your environment so that IDEs and terminal can compile C/C++ programs.

---

## 🔽 Task 1: Install MinGW

1. Download MinGW from the official SourceForge page:  
   🔗 https://sourceforge.net/projects/mingw/

2. Choose the **executable (.exe)** file to download, then exit the SourceForge page once downloaded.

3. Double-click the downloaded installer to start installation.

4. Click **Run** when the security prompt appears.

5. Click **Install** when the MinGW Setup window appears.

6. You may install MinGW anywhere.  
   **Recommended location** (leave default): C:\MinGW
   Click **Continue** to proceed.

7. The MinGW Installation Manager will open. Select the following packages by ticking them:
- `mingw32-base`
- `mingw32-gcc-g++`
- `msys-base`

8. From the top toolbar, select: Installation > Update Catalogue

9. After updating, open again: Installation > Apply Changes
   Then click **Apply**.

10. Wait until installation completes, then click **Close**.



---

## 🧩 Task 2: Add MinGW to System PATH (Environment Variables)

This step ensures your system and IDEs can find MinGW.

1. On the Windows search bar, type: Environment variable
   Press Enter.

2. Click the **Environment Variables…** button.

3. Under **System variables**, scroll and select **Path**, then click **Edit**.

4. Click **New** and add the MinGW `bin` directory path: C:\MinGW\bin


---

## 🧪 Verify Installation (Optional)

Open **Command Prompt (cmd)** and type:
```bash
gcc --version
g++ --version




