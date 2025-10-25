# Git 安装详细教程

## Windows 系统安装 Git

### 方法一：官方安装包（推荐）

1. **下载 Git**
   - 访问 Git 官网：https://git-scm.com/
   - 点击下载 Windows 版本

2. **运行安装程序**
   ```bash
   # 下载后运行 Git-2.xx.x-64-bit.exe
   ```

3. **安装步骤详解**

   **步骤 1：信息许可**
   - 点击 "Next"

   **步骤 2：选择安装路径**
   - 默认路径：`C:\Program Files\Git\`
   - 可以修改为其他路径

   **步骤 3：选择组件**
   ```bash
   # 建议选择：
   ☑ Git Bash Here
   ☑ Git GUI Here
   ☑ Git LFS (Large File Support)
   ☑ Associate .git* configuration files with the default text editor
   ☑ Associate .sh files to be run with Bash
   ```

   **步骤 4：选择开始菜单文件夹**
   - 默认即可，点击 "Next"

   **步骤 5：选择默认编辑器**
   ```bash
   # 推荐选择：
   - Use Visual Studio Code as Git's default editor
   # 或者选择其他编辑器：
   - Vim
   - Notepad++
   - Sublime Text
   ```

   **步骤 6：调整 PATH 环境**
   ```bash
   # 推荐选择（适合大多数用户）：
   ☑ Git from the command line and also from 3rd-party software
   
   # 其他选项：
   - Use Git from Git Bash only
   - Use Git and optional Unix tools from the Command Prompt
   ```

   **步骤 7：选择 SSH 执行文件**
   ```bash
   # 推荐选择：
   ☑ Use bundled OpenSSH
   ```

   **步骤 8：选择 HTTPS 传输后端**
   ```bash
   # 推荐选择：
   ☑ Use the OpenSSL library
   ```

   **步骤 9：配置行尾符号**
   ```bash
   # Windows 用户推荐：
   ☑ Checkout Windows-style, commit Unix-style line endings
   
   # 其他选项：
   - Checkout as-is, commit as-is
   - Checkout as-is, commit Unix-style line endings
   ```

   **步骤 10：配置终端模拟器**
   ```bash
   # 推荐选择：
   ☑ Use MinTTY (the default terminal of MSYS2)
   ```

   **步骤 11：选择默认行为**
   ```bash
   # 推荐选择：
   ☑ Default (fast-forward or merge)
   # 这个选项是关于 git pull 的默认行为
   ```

   **步骤 12：选择凭证助手**
   ```bash
   # 推荐选择：
   ☑ Git Credential Manager Core
   
   # 其他选项：
   - None
   - Git Credential Manager
   ```

   **步骤 13：配置额外选项**
   ```bash
   # 建议启用：
   ☑ Enable file system caching
   ☑ Enable symbolic links
   ```

   **步骤 14：实验性选项**
   - 通常保持默认不选
   - 点击 "Install"

4. **完成安装**
   - 安装完成后，勾选 "Launch Git Bash" 立即体验
   - 点击 "Finish"

### 方法二：包管理器安装

**使用 Chocolatey：**
```bash
# 安装 Chocolatey（如果尚未安装）
# 以管理员身份运行 PowerShell，然后执行：
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

# 使用 Chocolatey 安装 Git
choco install git -y
```

**使用 Winget：**
```bash
# Windows 11 自带
winget install Git.Git
```

## macOS 系统安装 Git

### 方法一：使用 Homebrew（推荐）

1. **安装 Homebrew**
   ```bash
   # 打开终端，执行：
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. **使用 Homebrew 安装 Git**
   ```bash
   brew install git
   ```

### 方法二：使用官方安装包

1. **下载 Git for macOS**
   - 访问：https://git-scm.com/download/mac
   - 下载最新版本的 .dmg 文件

2. **安装步骤**
   - 双击下载的 .dmg 文件
   - 将 Git 图标拖拽到 Applications 文件夹
   - 在 Launchpad 中找到并运行 Git

### 方法三：使用 Xcode Command Line Tools

```bash
# 在终端中执行：
xcode-select --install

# 如果已经安装，可以更新：
sudo xcode-select --switch /Library/Developer/CommandLineTools
```

## Linux 系统安装 Git

### Ubuntu / Debian

```bash
# 更新包列表
sudo apt update

# 安装 Git
sudo apt install git

# 验证安装
git --version
```

### 最新版本安装（Ubuntu）

```bash
# 添加官方 PPA
sudo add-apt-repository ppa:git-core/ppa
sudo apt update
sudo apt install git
```

### CentOS / RHEL / Fedora

**CentOS/RHEL:**
```bash
# CentOS 8+/RHEL 8+
sudo dnf install git

# CentOS 7/RHEL 7
sudo yum install git
```

**Fedora:**
```bash
sudo dnf install git
```

### Arch Linux

```bash
sudo pacman -S git
```

### openSUSE

```bash
sudo zypper install git
```

## 安装后验证

### 1. 验证安装
```bash
# 检查 Git 版本
git --version

# 应该输出类似：git version 2.39.1
```

### 2. 基本配置
```bash
# 配置用户信息（必需）
git config --global user.name "你的姓名"
git config --global user.email "你的邮箱@example.com"

# 检查配置
git config --list
```

### 3. 测试 Git 功能
```bash
# 创建一个测试目录
mkdir test-git
cd test-git

# 初始化 Git 仓库
git init

# 创建一个测试文件
echo "# Git Test" > README.md

# 添加到暂存区
git add README.md

# 提交更改
git commit -m "Initial commit"

# 查看提交历史
git log
```

## 图形界面工具（可选）

### Windows/macOS/Linux 通用

1. **GitKraken** - 强大的 Git 图形界面
   - 下载：https://www.gitkraken.com/

2. **Sourcetree** - Atlassian 出品的免费工具
   - 下载：https://www.sourcetreeapp.com/

3. **GitHub Desktop** - GitHub 官方工具
   - 下载：https://desktop.github.com/

### 集成开发环境中的 Git

- **Visual Studio Code** - 内置 Git 支持
- **IntelliJ IDEA** - 强大的 Git 集成
- **Eclipse** - 通过 EGit 插件支持

## 常见问题解决

### 问题 1：命令未找到
```bash
# Windows：确保在安装时选择了添加到 PATH
# 重启命令行工具或系统

# macOS/Linux：检查安装路径
which git
```

### 问题 2：权限问题
```bash
# Linux/macOS：使用 sudo
sudo apt install git  # Ubuntu/Debian

# 或者检查用户权限
groups $USER
```

### 问题 3：版本过旧
```bash
# 更新 Git
# Ubuntu/Debian：
sudo apt update && sudo apt upgrade git

# macOS（Homebrew）：
brew update && brew upgrade git
```

### 问题 4：SSL 证书问题
```bash
# 配置 Git 忽略 SSL 验证（不推荐，仅临时使用）
git config --global http.sslVerify false
```

## 下一步

安装完成后，建议：
1. 学习基本的 Git 命令
2. 配置 SSH 密钥用于远程仓库认证
3. 练习使用分支和合并
4. 了解 .gitignore 文件的使用

恭喜！你现在已经成功安装了 Git，可以开始版本控制之旅了！