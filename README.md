# 25-Alpha


## Repository Setup
**Make sure you are logged into GitHub as `841student`.** Ask a mentor for assistance if you need to log in.

### Clone this repository
Click on the green `<> Code` button and copy the URL `https://github.com/Team841/25-Alpha.git`
<img src="https://github.com/Team841/25-Alpha/blob/6a4490de7e4bc38de89edbfab2fabbcfa6580c2a/README%20images/git%20clone.png" width="300">

### Open in VSCode
* Open VSCode
* In the sidebar, select the 3rd item, `Source Control`. Looks like: branch with circles on the ends.
* `Clone Repository` and paste the URL from above. Hit `<enter>`.
<img src="https://github.com/Team841/25-Alpha/blob/26c6bdb7ae275f7367ead4a85beed891c9628d6a/README%20images/VsCode%20Clone%20Git.png" width="600">

* Change the location where this lives to `C:git`. If this location does not exist, create a new folder called `git` in `C:`

You should now have the repo set up in VSCode.

### After making some changes, "committing"
* "Stage" your all changes for "commit" to GitHub by clicking `+` in the Changes row of the Source Control browser, OR only some files by clicking `+` for each file you want to commit. *You must hover over this row to see the `+` button.*
* Type a descriptive message for the changes you are committing in the Message field
* Click `Commit` when you are done.
* `Sync Changes` to push your commit(s) to the remote (GitHub)

**Now, others will need to `Fetch` (circular arrows in the bottom left next to the branch name) to pull your changes.**

#### If you are the first to use this laptop
You may be prompted to sign in / authorize git
* *Make sure you are logged into GitHub as `841student`. Ask a mentor for assistance if you need to log in.*
* `Sign in with browser`
* `Authorize git-ecosystem`
* Go back to VSCode

If you get errors, talk to a mentor!

## Common errors
### Merge conflicts
You modified a file at the same time as someone else :(

*If you haven't dealt with this before, talk to a mentor or experienced Programmer before doing anything.*

* Stash your changes. In the Changes row of Source Control browser, reverse arrow with + sign, no message required
* Fetch / pull / Sync Changes again
* Source Control row [...] menu > Stash > Pop Latest Stash
* Resolve conflicts in the Merge Conflict editor. *Triple check to make sure your code still builds and does the thing you expect it to.*
* Commit again

## Setting up git
If you are attempting to set up git for the first time on this laptop, talk to a mentor or experienced Programmer before doing anything.

### Change the git identity
**Only needs to be done once per laptop**

Remove whatever was set on this computer before:
```
git config --global --unset user.name
git config --global --unset user.email
```

Set the new identity as the name of the current laptop, replacing `<laptop-name>` with the laptop's label name:
```
git config --global user.name "<laptop-name>"
```

### Merge conflicts
Set up merges with rebase so your local changes go *on top* of the changes that are already on the remote (GitHub). This makes your life easier in case of merge conflicts.
```
git config --global pull.rebase true
```

### Autosave in VSCode
Settings > Files: Auto Save -> afterDelay