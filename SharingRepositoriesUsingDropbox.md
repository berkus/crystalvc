# Sharing Repositories Using Dropbox #

[Crystal](http://crystalvc.googlecode.com) requires read-access to your co-workers' repositories. This document describes how to do this via Dropbox. There are four steps:

  1. [Install Dropbox](SharingRepositoriesUsingDropbox#1._Install_Dropbox.md)
  1. [Create the Dropbox folder](SharingRepositoriesUsingDropbox#2._Create_the_Dropbox_folder.md)
  1. [Link your repository to Dropbox](SharingRepositoriesUsingDropbox#3._Link_your_repository_to_Dropbox.md)
  1. [Use Crystal with Dropbox](SharingRepositoriesUsingDropbox#4._Use_Crystal_with_Dropbox.md)

# 1. Install Dropbox #

Sign up for [Dropbox](http://www.dropbox.com) and install the application.

We will call the Dropbox folder MY\_DROPBOX\_FOLDER. It defaults to ~/Dropbox/ under Unix-like environments, and to My Dropbox under Windows.

If you are a Linux user and need to install Dropbox without root access, see [this page](InstallingDropboxWithoutRootPrivileges.md).

# 2. Create the Dropbox folder #

Only one person has to perform this step, but it must be performed before anyone can use Dropbox. If you are not responsible for this step, then you should have already received an invitation to use the shared Dropbox folder. In that case, skip to Link your repository to Dropbox. **If you are not the person performing this state and you have not received an invitation, do not proceed until you get one!**

In MY\_DROPBOX\_FOLDER, create a folder called PROJECT.

Follow the instructions at [Link your repository to Dropbox](SharingRepositoriesUsingDropbox#Link_your_repository_to_Dropbox.md) for the master repository, using the folder name "master" and the location of the master repository.

At http://www.dropbox.com, select the PROJECT folder and click on "shared folder options". Share the folder with the other relevant users. (You may also use the "Invite to Dropbox" link from the "get started" tab on http://www.dropbox.com to send them an official invite to Dropbox, but this is not necessary.)

Send email to your collaborators with a link to this document, and telling them the name of the Dropbox folder.

# 3. Link your repository to Dropbox #

> 3.1. For each of your repositories, create a folder.

> If your username is jsmith, and you have only have one clone of the master, then you might create `MY_DROPBOX_FOLDER/PROJECT/jsmith`.

> If you have multiple clones, then create a directory for each one. Here are possible names:

```
    MY_DROPBOX_FOLDER/PROJECT/jsmith-desktop
    MY_DROPBOX_FOLDER/PROJECT/jsmith-laptop
    MY_DROPBOX_FOLDER/PROJECT/jsmith-experimentalbranch
```

> 3.2. Clone the master, or link an existing clone. This step depends on your operating system.
    * If you use Windows, then you must clone your repository from the master to MY\_DROPBOX\_FOLDER/PROJECT/jsmith, and use that for all your work.
    * If you use a Unix-like system (Linux/Mac), then you can clone the repository from the master to wherever you want. Then, create a symbolic link to the repository to that location in MY\_DROPBOX\_FOLDER/PROJECT/jsmith. For example:

```
      ln -s WORKING_COPY/.hg ~/Dropbox/PROJECT/jsmith/PROJECT/.hg
```

> where WORKING\_COPY is the "working copy" or "repository root" — the directory that contains a `.hg/` subdirectory, which is the repository itself.

> 3.3. Tell your collaborators about the new MY\_DROPBOX\_FOLDER/PROJECT/jsmith/PROJECT folder, so that they can add it to their [Crystal configuration file](SharingRepositoriesUsingDropbox#Use_Crystal_with_Dropbox.md).

# 4. Use Crystal with Dropbox #

Download and set up [Crystal](http://crystalvc.googlecode.com), as described in the [user manual](CrystalUserManual.md).

When you set up the configuration file, use each of your collaborators' directories, such as

```
  MY_DROPBOX_FOLDER/PROJECT/jdoe/PROJECT
  MY_DROPBOX_FOLDER/PROJECT/jqpublic/PROJECT
```

If more users add their repositories to Dropbox after you set up Crystal, then you will need to update the configuration file.