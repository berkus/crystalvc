# Installing Dropbox on Linux Without Root Privileges #

Here are instructions for installing dropbox on Linux, whether or not you are a root user (an administrator). For instance, these instructions should work on a centrally-administered computer cluster. (You don't need the "Nautilus integration" which does not provide or affect Dropbox functionality, just modifies the icons when you open a Dropbox folder in the graphical file browser Nautilus.)

```
# Install the command-line Dropbox frontend script, in ~/bin/share, and run
dropbox.  Adjust the directory to your liking.
mkdir -p ~/bin/share
cd ~/bin/share
wget -O dropbox.py "http://www.dropbox.com/download?dl=packages/dropbox.py"
chmod 755 dropbox.py
# For a usage message:  ./dropbox.py help
./dropbox.py start -i
./dropbox.py autostart y 
```

You may also want to add the following to your crontab, because the daemon crashes sometimes.

```
# Ensure that Dropbox daemon is running
0 * * * *	if [ "`$HOME/bin/share/dropbox.py status`" = 'Dropbox isn'"'"'t running!' ] ; then $HOME/bin/share/dropbox.py start ; fi
```