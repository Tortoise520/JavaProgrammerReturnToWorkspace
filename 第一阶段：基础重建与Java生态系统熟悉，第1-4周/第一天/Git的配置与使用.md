# Git的配置与使用
好的，这是一份非常全面的 Git 配置与使用指南，从入门到进阶，涵盖了最常用的概念和操作。

---

### **第一部分：Git 配置**

在开始使用 Git 之前，你需要进行一些全局配置，这非常重要，因为你的每一次提交都会记录这些信息。

#### 1.1 用户身份配置
这是最重要的第一步，设置你的用户名和邮箱。

```bash
# 设置全局用户名
git config --global user.name "你的姓名"

# 设置全局邮箱
git config --global user.email "你的邮箱@example.com"

# 查看所有配置项
git config --list
```

**说明：**
*   `--global` 选项表示这是全局配置，意味着你这台机器上所有的 Git 仓库都会使用这个配置。如果只想对某个特定仓库配置，去掉 `--global` 即可。
*   这个信息会写入你的每一次提交中，用于标识提交者的身份。

#### 1.2 其他常用配置

```bash
# 设置默认分支名为 main（现代 Git 版本默认已是 main，但显式设置是个好习惯）
git config --global init.defaultBranch main

# 让命令行输出更易读（颜色高亮）
git config --global color.ui auto

# 设置常用的命令别名（快捷方式）
git config --global alias.co checkout    # 使用 git co 代替 git checkout
git config --global alias.br branch      # 使用 git br 代替 git branch
git config --global alias.ci commit      # 使用 git ci 代替 git commit
git config --global alias.st status      # 使用 git st 代替 git status

# 设置默认的文本编辑器（当 Git 需要你输入信息时使用）
git config --global core.editor "code --wait"  # 使用 VSCode
# 对于 Vim: git config --global core.editor "vim"
```

---

### **第二部分：Git 核心概念与工作流程**

理解这些概念是熟练使用 Git 的关键。

*   **工作区 (Working Directory)**：你直接编辑文件的地方。
*   **暂存区 (Staging Area / Index)**：一个中间区域，存放你打算下次提交的更改。
*   **仓库 (Repository)**：存放项目所有历史版本和元数据的地方，位于 `.git` 目录。
*   **分支 (Branch)**：一条独立的开发线，默认分支通常是 `main` 或 `master`。
*   **提交 (Commit)**：一个快照，记录了仓库在某个时间点的状态。

**基本工作流程：**
1.  在工作区修改文件。
2.  将文件的更改**添加 (add)** 到暂存区。
3.  将暂存区的内容**提交 (commit)** 到仓库，形成一个永久的历史记录。

---

### **第三部分：日常使用命令**

#### 3.1 创建仓库与克隆

```bash
# 在当前目录初始化一个新的 Git 仓库
git init

# 从远程服务器克隆一个已存在的仓库（最常用）
git clone <远程仓库URL>
# 例如：git clone https://github.com/username/repo.git
```

#### 3.2 查看状态与差异

```bash
# 查看工作区和暂存区的状态（最常用的命令之一）
git status

# 显示工作区与暂存区的文件差异
git diff

# 显示暂存区与最新提交的差异
git diff --staged
```

#### 3.3 添加与提交

```bash
# 将指定文件添加到暂存区
git add <文件名>

# 将所有更改的文件添加到暂存区
git add .

# 将暂存区的内容提交到仓库，并附上提交信息
git commit -m "你的提交描述信息"

# 一个快捷方式：跳过 git add，直接提交所有已跟踪文件的更改
#（注意：新文件不会被添加）
git commit -a -m "提交描述信息"
```

#### 3.4 查看历史

```bash
# 查看提交历史
git log

# 以单行简洁模式查看历史
git log --oneline

# 查看包含文件变更历史的图形化日志
git log --graph --oneline --decorate --all
```

#### 3.5 分支管理

```bash
# 查看所有分支
git branch

# 创建新分支
git branch <新分支名>

# 切换到指定分支
git checkout <分支名>
# 或者使用更现代的 switch 命令
git switch <分支名>

# 创建并切换到新分支（常用快捷方式）
git checkout -b <新分支名>
git switch -c <新分支名>

# 将指定分支合并到当前分支
git merge <要合并过来的分支名>

# 删除分支（如果分支已被合并）
git branch -d <分支名>
# 强制删除分支（即使未被合并）
git branch -D <分支名>
```

#### 3.6 远程仓库协作

```bash
# 查看远程仓库地址别名（通常 origin 是默认的远程仓库名）
git remote -v

# 将本地提交推送到远程仓库
git push origin <分支名>
# 例如，首次推送 main 分支并建立追踪关系
git push -u origin main
# 之后可以直接使用
git push

# 从远程仓库拉取更新并合并到当前分支（常用）
git pull origin <分支名>
# 等同于 git fetch + git merge

# 仅从远程仓库下载更新，但不合并
git fetch origin

# 将远程仓库的最新更改合并到你的本地分支（在 git fetch 之后使用）
git merge origin/main
```

---

### **第四部分：进阶操作与场景**

#### 4.1 撤销操作

```bash
# 撤销工作区的修改（危险！会丢失更改）
git checkout -- <文件名>

# 将文件从暂存区移回工作区（取消 add）
git reset HEAD <文件名>

# 撤销最近的一次提交，并将更改放回工作区
git reset --soft HEAD~1

# 完全丢弃最近的一次提交和所有更改（危险！）
git reset --hard HEAD~1

# 创建一个新的提交来撤销指定提交的更改（安全，推荐用于公共分支）
git revert <commit_id>
```

#### 4.2 储藏更改

当你想临时切换分支但又不想提交未完成的工作时。

```bash
# 储藏当前工作区和暂存区的更改
git stash

# 查看储藏列表
git stash list

# 恢复最近储藏的更改
git stash pop

# 应用某次储藏，但不从储藏列表中删除
git stash apply stash@{0}
```

#### 4.3 解决冲突

当 `git merge` 或 `git pull` 遇到冲突时：
1.  Git 会标记出文件中的冲突部分（`<<<<<<<`, `=======`, `>>>>>>>`）。
2.  手动编辑文件，解决这些冲突，保留你想要的内容。
3.  使用 `git add <文件名>` 将解决后的文件标记为已解决。
4.  使用 `git commit` 来完成合并操作。

---

### **第五部分：推荐的工作流程**

对于初学者，推荐使用 **「功能分支工作流」**：

1.  **主分支保持稳定**：`main` 分支的代码永远是可用的。
2.  **开发新功能**：每次开发新功能或修复 Bug 时，都从 `main` 分支创建一个新的功能分支（如 `git switch -c feature/awesome-feature`）。
3.  **在功能分支上工作**：在该分支上进行提交。
4.  **推送并合并**：完成功能后，将分支推送到远程仓库，并发起一个 **Pull Request (PR)** 或 **Merge Request (MR)**，请求将你的代码合并回 `main` 分支。
5.  **代码审查与合并**：团队成员审查代码后，将其合并到 `main` 分支。
6.  **删除功能分支**：合并成功后，删除本地的和远程的功能分支。

---

### **总结**

| 任务 | 命令 |
| :--- | :--- |
| **初次设置** | `git config --global user.name "..."` `git config --global user.email "..."` |
| **克隆仓库** | `git clone <url>` |
| **查看状态** | `git status` |
| **添加文件** | `git add <file>` 或 `git add .` |
| **提交更改** | `git commit -m "message"` |
| **查看历史** | `git log --oneline` |
| **创建分支** | `git switch -c <branch-name>` |
| **切换分支** | `git switch <branch-name>` |
| **合并分支** | `git merge <branch-name>` |
| **推送到远程** | `git push -u origin <branch-name>` |
| **拉取更新** | `git pull` |
| **撤销工作区修改** | `git restore <file>` |
| **储藏工作** | `git stash`, `git stash pop` |

实践是学习 Git 的最佳方式。建议你在本地创建一个测试项目，反复练习这些命令，很快你就会得心应手。对于更复杂的操作（如 `rebase`），建议在掌握基础后再深入学习。