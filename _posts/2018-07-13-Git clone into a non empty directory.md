---
layout: post
title: How do I clone into a non empty directory with git?
categories: [Git]
tags: [Git]
fullview: false
comments: true
---

* 1, 方法一：

```sh

git init
git remote add origin PATH/TO/REPO
git fetch
git reset origin/master  # this is required if files in the non-empty directory are in the repo
git checkout -t origin/master

```

* 2, 方法二：

```sh
# Clone just the repository's .git folder (excluding files as they are already in
# `existing-dir`) into an empty temporary directory
git clone --no-checkout repo-to-clone existing-dir/existing-dir.tmp # might want --no-hardlinks for cloning local repo

# Move the .git folder to the directory with the files.
# This makes `existing-dir` a git repo.
mv existing-dir/existing-dir.tmp/.git existing-dir/

# Delete the temporary directory
rmdir existing-dir/existing-dir.tmp
cd existing-dir

# git thinks all files are deleted, this reverts the state of the repo to HEAD.
# WARNING: any local changes to the files will be lost.
git reset --hard HEAD
```