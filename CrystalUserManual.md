# Crystal User Manual #

<a href='http://code.google.com/p/crystalvc/downloads/detail?name=crystal_2.0.20111120.jar'><img src='http://crystalvc.googlecode.com/hg/webpage/download.png' alt='download Crystal' height='75' /></a>

## Table of Contents: ##

  * [Crystal's goal](CrystalUserManual#Goal:_Increased_developer_awareness_of_conflicts.md)
  * [How to run Crystal](CrystalUserManual#How_to_run_Crystal.md)
    * [Download and installation](CrystalUserManual#Download_and_installation.md)
    * [Preventing Crystal from asking for a password](CrystalUserManual#Preventing_Crystal_from_asking_for_a_password.md)
  * [What Crystal displays](CrystalUserManual#What_Crystal_displays.md)
    * [Local state](CrystalUserManual#Local_state.md)
    * [Relationship](CrystalUserManual#Relationship.md)
    * [Action](CrystalUserManual#Action.md)
    * [Guidance](CrystalUserManual#Guidance.md)
  * [Configuration file format](CrystalUserManual#Configuration_file_format.md)
  * [Making your repository available to your co-workers](CrystalUserManual#Making_your_repository_available_to_your_co-workers.md)
    * [Making a copy of your repository](CrystalUserManual#Making_a_copy_of_your_repository.md)
  * [Troubleshooting](CrystalUserManual#Troubleshooting.md)
  * [Log files](CrystalUserManual#Log_files.md)
  * [Publications](CrystalUserManual#Publications.md)
  * [Acknowledgements](CrystalUserManual#Acknowledgements.md)
  * [Contacts](CrystalUserManual#Contacts.md)


# Goal: Increased developer awareness of conflicts #

When two or more developers collaborate, it is possible for their independent changes to conflict — either syntactically as a version control conflict or behaviorally if the changes merge cleanly but have unintended interactions.

The Crystal tool informs each developer of the answer to the question, “Might my changes conflict with others' changes?”

Crystal monitors multiple developers' repositories. It informs each developer when it is safe to push her changes, when she has fallen behind and could pull changes from others or a central repository, and when changes other developers have made will cause a syntactic or behavioral conflict.

Crystal examines commits. It does not examine your working copy — your uncommitted modifications. The reason is that commits are more likely to be coherent and desired units of work, for which notification about (non-)conflicts is of value.

  * If conflicts occur, Crystal informs developers early, so they may resolve these conflicts quickly. Long-established conflicts can be much harder to resolve.
  * If changes are made without conflicts, Crystal gives developers confidence to merge their changes without fearing unanticipated side effects.

[Download Crystal](http://crystalvc.googlecode.com/hg/webpage/crystal.jar)

# How to run Crystal #

To run Crystal, either double click the crystal.jar file or execute from the command line:

> java -jar crystal.jar

Crystal runs as an icon in your task bar. Click on the icon to see the full client and more options.

## Download and installation ##

Crystal has the following requirements:

  * JRE (Java Runtime Environment).
  * Mercurial, version 1.6 or later or Git.
  * The more of your co-workers' repositories you have read access to, the more useful Crystal will be. However, Crystal can be useful even if you only have access to your repository's parent.

To install Crystal:

  * Download crystal.jar.  (The current version is 2.0.20111120. To learn the version of an executable, run java -jar crystal.jar --version or select the about menu in the GUI.)
  * Run Crystal: java -jar crystal.jar
  * The first time that you run Crystal, it will complain that your configuration file is invalid and give you instructions on how to fix it. You can do so either via a GUI that creates the configuration file for you, or by editing the configuration file directly. See below for the configuration file format.
  * Restart Crystal.

## Preventing Crystal from asking for a password ##

If accessing the remote repositories in your configuration file prompts you for a password, then Crystal will forward that prompt to you. Crystal accesses these repositories frequently — by default, every 10 minutes for each repository.

Depending on how you access your repositories, there are ways to get around entering your password every time:

If you use ssh to access the repositories, you can use the instructions for plink (comes with the Windows installation of Mercurial and Git) or instructions for ssh agent.
If you use http or https to access the repositories, you can enter the passwords in plain text in your Mercurial or Git configuration file.

# What Crystal displays #

This is a screen shot of the main Crystal window.

![http://crystalvc.googlecode.com/hg/webpage/CrystalScreenShot.png](http://crystalvc.googlecode.com/hg/webpage/CrystalScreenShot.png)

Crystal displays four types of information: local state, relationship, possible action, and guidance.

## Local state ##

The "local state", shown below the project name, describes the working copy. It shows a command that can be run on the working copy.

  1. If there are uncommitted local changes, it shows hg commit
  1. If there are unresolved conflicts, it shows hg fetch
  1. Otherwise, it is blank

## Relationship ##

There are seven possible relationships (plus two descriptors) between the developer's repository and that of a collaborate. This relationship determines the shape of the icon Crystal displays.  Each relationship may come in one of three shadings.

| **Crystal shape** | **Meaning of the shape** |
|:------------------|:-------------------------|
| <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/same.png' alt='solid green checkmark' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/same.png' alt='partially saturated green checkmark' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/same.png' alt='hollow green checkmark' height='48' /> | SAME: The repositories are in sync.|
| <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/ahead.png' alt='solid green up arrow' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/ahead.png' alt='partially saturated green up arrow' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/ahead.png' alt='hollow green up arrow' height='48' /> | AHEAD: Your repository has newer commits than the other one. You may consider pushing your changes or letting the owner know.|
| <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/behind.png' alt='solid green down arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/behind.png' alt='partially saturated green down arrow' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/behind.png' alt='hollow green down arrow' height='48' /> <table><thead><th> BEHIND: The other repository has newer commits than yours. You may consider pulling changes to avoid later merges.</th></thead><tbody>
<tr><td> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/merge.png' alt='solid yellow merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/merge.png' alt='partially saturated yellow merge' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/merge.png' alt='hollow yellow merge' height='48' /> </td><td> MERGE: Each of the two repositories has commits not present in the other one, but they can be merged cleanly.</td></tr>
<tr><td> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/mergeconflict.png' alt='solid red merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/mergeconflict.png' alt='partially saturated red merge' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/mergeconflict.png' alt='hollow red merge' height='48' /> </td><td> TEXTUAL_X: Each of the two repositories has commits not present in the other one, and merging them will result in a textual conflict.</td></tr>
<tr><td> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/compileconflict.png' alt='solid red merge with B' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/compileconflict.png' alt='partially saturated red merge with B' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/compileconflict.png' alt='hollow red merge with B' height='48' /> </td><td> BUILD_X: Each of the two repositories has commits not present in the other one, they can be merged textually cleanly, but merging them results in code that fails to build.</td></tr>
<tr><td> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/testconflict.png' alt='solid red merge with T' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/testconflict.png' alt='partially saturated red merge with T' height='48' /> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/testconflict.png' alt='hollow red merge with T' height='48' /> </td><td> TEST_X: Each of the two repositories has commits not present in the other one, they can be merged textually cleanly, but merging them results in code that fails tests.</td></tr>
<tr><td> <img src='http://crystalvc.googlecode.com/hg/webpage/clock.png' alt='clock' height='48' /> </td><td> Crystal is in the process of refreshing this data.</td></tr>
<tr><td> <img src='http://crystalvc.googlecode.com/hg/webpage/error.png' alt='error' height='48' /> </td><td> Crystal experienced an error in computing this relationship.</td></tr></tbody></table>

The icons' shading has the following meaning:<br>
<br>
<table><thead><th> <b>Icon shading</b> </th><th> <b>Meaning of the shading</b> </th></thead><tbody>
<tr><td> Solid <br> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/same.png' alt='solid green checkmark' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/behind.png' alt='solid green down arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/ahead.png' alt='solid green up arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/merge.png' alt='solid yellow merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/mergeconflict.png' alt='solid red merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/compileconflict.png' alt='solid red merge with B' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/must/testconflict.png' alt='solid red merge with T' height='48'> </td><td> You must be the one who affects this relationship, and you can do so now.</td></tr>
<tr><td> Partially saturated <br> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/same.png' alt='partially saturated green checkmark' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/behind.png' alt='partially saturated green down arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/ahead.png' alt='partially saturated green up arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/merge.png' alt='partially saturated yellow merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/mergeconflict.png' alt='partially saturated red merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/compileconflict.png' alt='partially saturated red merge with B' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/might/testconflict.png' alt='partially saturated red merge with T' height='48'> </td><td> You might be the one who affects this relationship, and you might be able to do so now or later.</td></tr>
<tr><td> Hollow <br> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/same.png' alt='hollow green checkmark' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/behind.png' alt='hollow green down arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/ahead.png' alt='hollow green up arrow' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/merge.png' alt='hollow yellow merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/mergeconflict.png' alt='hollow red merge' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/compileconflict.png' alt='hollow red merge with B' height='48'> <img src='http://crystalvc.googlecode.com/hg/src/crystal/client/images/128X128/cannot/testconflict.png' alt='hollow red merge with T' height='48'> </td><td> You cannot be the one who affects this relationship, neither now nor later.</td></tr></tbody></table>


In the task bar, Crystal displays the most severe relationship icon that appears anywhere in the full window.<br>
<br>
<h2>Action</h2>

Holding the mouse over an icon displays a tool tip. At the top of the tool tip, Crystal shows the action the developer may perform, if one is available (e.g., hg commit, hg merge, hg fetch, and hg push).<br>
<br>
<h2>Guidance</h2>

There are five types of guidance Crystal displays:<br>
<br>
<ol><li>ommitter: the list of users whose changes are causing the relationship. Crystal displays this information in the tool tip.<br>
</li><li>hen: whether or not the developer can act right now to affect this relationship. The relationship icon is solid if the developer can act now, and hollow if the developer cannot act until later, after some other developer has performed an action.<br>
</li><li>apable: whether or not the developer must, might, or cannot act to affect the relationship. The relationship icon is solid if the developer must be the one to take action, solid but unsaturated if the developer might be the one to take action, and hollow if the developer cannot be the one to take action.<br>
</li><li>onsequences: the new relationship after the developer executes the available action. Crystal displays this information in the tool tip.<br>
</li><li>ase: Whether another developer may have an easier time affecting this relationship. Crystal does not yet display this information in the tool tip, but future versions will.</li></ol>


<h1>Configuration file format</h1>

The Crystal configuration file is an XML file that describes the locations of the scratch space, the <code>hg</code> executable, and the repositories Crystal monitors. On a Unix-like environment, it appears in <code>~/.conflictClient.xml</code>. On a Windows environment, it appears in the user's home directory, e.g., <code>%UserProfile%\.conflictClient.xml</code>.<br>
<br>
Here is an example valid configuration file:<br>
<pre><code>&lt;?xml version="1.0" encoding="UTF-8"?&gt;<br>
&lt;ccConfig tempDirectory="/scratch/conflictClient/" refresh="60"&gt;<br>
  &lt;!-- First project uses dropbox to share repositories --&gt;<br>
  &lt;project ShortName="MyFirstProject" Kind="HG" Clone="$HOME/Dropbox/projects/MyLocalFirstProjectRepo/" parent="MASTER"&gt;<br>
    &lt;source ShortName="MASTER" Clone="$HOME/Dropbox/projects/MASTER/MyLocalFirstProjectRepo/" commonParent="MASTER" /&gt;<br>
    &lt;source ShortName="Friend" Clone="$HOME/Dropbox/projects/Friend/MyLocalFirstProjectRepo" commonParent="MASTER" /&gt;<br>
    &lt;source ShortName="Enemy" Clone="$HOME/Dropbox/projects/Enemy/MyLocalFirstProjectRepo" commonParent="MASTER" /&gt;<br>
  &lt;/project&gt;<br>
  &lt;!-- Second project does not use dropbox to share repositories --&gt;<br>
  &lt;project ShortName="MySecondProject" Kind="HG" Clone="$HOME/projects/MyLocalSecondProjectRepo/"<br>
           parent="MASTER" compile="make" test="make test"&gt;<br>
    &lt;source ShortName="MASTER" Clone="ssh://user@host/path/to/second/project/repo/" commonParent="MASTER" /&gt;<br>
    &lt;source ShortName="Friend" Clone="https://user@host/path/to/friend/second/repo" commonParent="MASTER" /&gt;<br>
  &lt;/project&gt;<br>
&lt;/ccConfig&gt;<br>
</code></pre>

To be a valid XML file, it cannot have any whitespace before the <code>&lt;?xml ...?&gt;</code> element.<br>
A configuration file consists of a single XML element: <code>ccConfig</code>. This element has two attributes: <code>tempDirectory</code> and (optionally) <code>refresh</code>. These attributes specify the path to the scratch space and how often, in seconds, Crystal should attempt to refresh itself. <code>ccConfig</code> has a <code>project</code> child for each project that Crystal monitors.<br>
<br>
The project XML element has 7 attributes; the first three are required and the last four are optional.<br>
<br>
<ul><li><code>ShortName</code>: the name of the project that Crystal will display on the left side on the main window<br>
</li><li><code>Kind</code>: the DVCS; currently, must be HG<br>
</li><li><code>Clone</code>: the path to your local repository. This may be any address that is a valid argument to the <code>hg clone</code> command. Typically, this is a local path to a directory that contains a <code>.hg</code> directory.<br>
</li><li><code>parent</code>: the shortName of the repository that is your repository's parent; that is, the default place you push to and pull from. Crystal needs to know the parent to determine your possible actions; if you do not supply the parent attribute, Crystal will not report the action information.<br>
</li><li><code>RemoteHG</code>: necessary only if the <code>--remotecmd</code> option is necessary to specify the path to <code>hg</code> on the server where your local repository resides; the value of this element is passed directly to the <code>hg</code> command with the <code>--remotecmd</code> option.<br>
</li><li><code>compile</code>: a command to execute to compile the project, such as "<code>make</code>"<br>
</li><li><code>test</code>: a command to execute to run the project's tests, such as "<code>make test</code>"</li></ul>

The <code>project</code> element has a <code>source</code> child for each remote repository that Crystal should compare to your repository.<br>
<br>
The <code>source</code> element has four attributes. The first two are required and the last two are optional.<br>
<br>
<ul><li><code>ShortName</code>: the name of the repository that Crystal to displays above the relationship icon in the main window.<br>
</li><li><code>Clone</code>: the path to this repository. This can be any address that is a valid argument to <code>hg clone</code>.<br>
</li><li><code>commonParent</code>: the shortName of the repository that is the common parent between your repository and the repository this source element represents. Most often, the common parent is the master repository. Crystal needs to know the common parent to determine the guidance information; if you do not supply the commonParent attribute, Crystal will not report any guidance and all relationship icons will appear solid.<br>
</li><li><code>RemoteHG</code>: only necessary if the <code>--remotecmd</code> option is necessary to specify the path to <code>hg</code> on the server where the repository this source element represents resides; the value of this element is passed directly to the <code>hg</code> command with the <code>--remotecmd</code> option.</li></ul>

<h1>Making your repository available to your co-workers</h1>

The more of your co-workers' repositories you have read access to, the more useful Crystal will be. This section explains how to make your repositories (your clones) available to a co-worker.<br>
<br>
If you and your co-worker have access to the same file system, then you can use the "File system sharing" technique. If you have access to a machine that runs a web server, then you can use the "Http sharing" technique. You can always use the "Dropbox sharing" technique.<br>
<br>
File system sharing<br>
<blockquote>If you and your co-worker have access to the same file system, then you can store your repository in a place where your co-worker can read it.</blockquote>

<blockquote>You can either grant your co-worker read permission to your repository, or you can copy your repository to a location that your co-worker can read.</blockquote>

Dropbox sharing<br>
<blockquote>You can use the <a href='http://www.dropbox.com'>Dropbox</a> file sharing service to share your repository with your co-workers. This approach has several benefits: changes are copied immediately, and the same technique works for all co-workers. For full details, see the document <a href='SharingRepositoriesUsingDropbox.md'>Sharing repositories using Dropbox</a>.</blockquote>

Http sharing<br>
<blockquote>The http sharing approach is often easier, but it only works if you have access to a machine that runs a web server. Either make your repository accessible via http, or periodically copy your repository to a location that is accessible via http. In other words, the location will be a directory that has a http: URL.<br>
<ul><li>To make your repository accessible via http, one way is to make a symbolic link from your <code>~/public_html</code> (or similar) directory (this may require changing access permissions so the web server can read your repository).<br>
</li><li>If you make a copy of your repository, then don't forget to make <code>.../accessible-path/</code> and all subdirectories readable by the web server (example: <code>chmod -R og+r .../accessible-path</code>), and also enable the web server to show a directory listing to the <code>hg</code> client (example: add <code>+Indexes</code> to file <code>.../accessible-path/.htaccess</code>).</li></ul></blockquote>

<h2>Making a copy of your repository</h2>

If you choose to make a copy of your repository in an accessible location, then the more frequently you update the copy, the more useful Crystal is. Here is a line you can place in your crontab file to automate the task:<br>
<br>
<pre><code># Update a public copy of a `.hg` directory every minute.<br>
* * * * *	rsync -a .../path-to-repo/.hg .../accessible-path<br>
</code></pre>

The <code>.hg</code> directory will end up as <code>.../accessible-path/.hg</code>, and the path to the repository is just <code>.../accessible-path</code>.<br>
<br>
The <code>rsync</code> program even permits the destination path to be on a different computer, in which case it looks like <code>machinename:filename</code>, for example <code>barb.cs.washington.edu:/homes/gws/mernst/www/crystal-repositories/...</code>

<h1>Troubleshooting</h1>

<ul><li>If Crystal complains that it cannot load the <code>.conflictClient.xml</code> file and the error message contains "<code>&gt; &gt; &gt; null</code>", your <code>.conflictClient.xml</code> file is invalid:<br>
<ul><li>Make sure there is a <code>&lt;?xml ... &gt;</code> element.<br>
</li><li>Make sure the <code>&lt;?xml ... &gt;</code> element contains no blank lines or spaces before it.<br>
</li><li>Make sure all the required elements are present.<br>
</li></ul></li><li>If Crystal reports that it can't run Mercurial or Git:<br>
<ul><li>Make sure you have the latest version of Mercurial. Crystal will let you know the oldest compatible version. Repositories created with newer versions do not always operate with older versions of the tool.<br>
</li><li>If you created a symbolic link to your <code>.hg</code> directory, make sure it actually points where you think it does by browsing it.</li></ul></li></ul>

<h1>Log files</h1>

Crystal maintains two log files to help with diagnosing unexpected problems. One is a plain text log that is easy to read (<code>.conflictClientLog.log</code>) and the other is an XML log that can be more easily analyzed programmatically (<code>.conflictClientLog.xml</code>). On a Unix-like environment, these appear in <code>~/</code>. On a Windows environment, these appears in the user's home directory <code>%UserProfile%\</code>.<br>
<br>
<h1>Publications</h1>

See the <a href='Publications.md'>publications</a> page for the most recent list of publications about Crystal.<br>
<br>
<h1>Acknowledgements</h1>

This work is supported by Microsoft Research through the Software Engineering Innovation Foundation grant, by the National Science Foundation under Grant# 0963757 and Grant #0937060 to the Computing Research Association for the CIFellows Project, by the National Science and Engineering Research Council Postdoctoral Fellowship, and by IBM through a John Backus Award.<br>
<br>
<h1>Contacts</h1>

Crystal is designed and developed by<br>
<a href='http://people.cs.umass.edu/~brun'>Yuriy Brun</a>, <a href='http://www.cs.uwaterloo.ca/~rtholmes'>Reid Holmes</a>, <a href='http://www.cs.washington.edu/homes/mernst'>Michael Ernst</a>, and <a href='http://www.cs.washington.edu/homes/notkin'>David Notkin</a>.<br>
<br>
<a href='mailto:crystalvc@googlegroups.com'>Email us</a> with any questions.