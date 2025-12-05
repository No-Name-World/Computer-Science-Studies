# 🛠️ C/C++ Setup Guide (Code::Blocks + MinGW Installation on Windows)

This guide explains how to install **MinGW (Minimalist GNU for Windows)** and configure your environment so that IDEs and terminal can compile C/C++ programs. It also includes **Code::Blocks setup** for C/C++ development.

---

## 🖥️ Task 0: Install Code::Blocks (with MinGW Compiler)

To use an IDE for C/C++ coding, download Code::Blocks here:

🔗 https://sourceforge.net/projects/codeblocks/

### 📝 Important
This version already contains the MinGW compiler.

### 📌 If your Code::Blocks does NOT include MinGW
Then follow **Task 1 and Task 2** below to install MinGW separately and configure the path.

---

## 🔽 Task 1: Install MinGW (Optional if Code::Blocks has compiler)

> Only follow this if Code::Blocks was installed **without** MinGW.

1. Download MinGW from the official SourceForge page:  
   🔗 https://sourceforge.net/projects/mingw/

2. Choose the **executable (.exe)** file to download, then exit the SourceForge page once downloaded.

3. Double-click the downloaded installer to start installation.

4. Click **Run** when the security prompt appears.

5. Click **Install** when the MinGW Setup window appears.

6. Recommended installation path (leave default): C:\MinGW
   Click **Continue**.

7. In MinGW Installation Manager, tick the following packages:
- `mingw32-base`
- `mingw32-gcc-g++`
- `msys-base`

8. From the top toolbar, select: Installation > Update Catalogue
   
9. Then open again: Installation > Apply Changes
   Click **Apply**.

10. Wait until installation completes, then click **Close**.

---

## 🧩 Task 2: Add MinGW to System PATH (Environment Variables)

> Skip this if MinGW was already recognized in Code::Blocks.

1. On the Windows search bar, type: Environment variable
   Press Enter.

2. Click the **Environment Variables…** button.

3. Under **System variables**, select **Path**, then click **Edit**.

4. Click **New** and add the following directory: C:\MinGW\bin


---

## 🧪 Verify MinGW Installation (Optional)

Open **Command Prompt (cmd)** and type:
```bash
gcc --version
g++ --version
```

If version information appears, MinGW is installed successfully 🎉


