Install Ansible
=============

Mac:
----
Install Python setuptools: https://pypi.python.org/pypi/setuptools#installation-instructions

```sudo easy_install pip```
```sudo pip install paramiko PyYAML jinja2 passlib```
```sudo pip install ansible --quiet```

Then, if you're going to update ansible later, just do:

```sudo pip install ansible --upgrade```

Other:
------
http://www.ansibleworks.com/docs/intro_installation.html

Install VirtualBox
==================
https://www.virtualbox.org/wiki/Downloads

Install Vagrant
===============
http://docs.vagrantup.com/v2/installation/

Run: ```vagrant up```

Map the ip addresses of the VMs in your /etc/hosts
---------------------------------------------------
```
192.168.101.10 db.local db
192.168.101.11 app1.local app1
192.168.101.12 app2.local app2
192.168.101.13 proxy.local proxy
```

Prevent StrictHostKeyChecking by adding the following to ~/.ssh/config
--------

Host db.local
StrictHostKeyChecking no
UserKnownHostsFile /dev/null
User vagrant
LogLevel QUIET

Host app1.local
StrictHostKeyChecking no
UserKnownHostsFile /dev/null
User vagrant
LogLevel QUIET

Host app2.local
StrictHostKeyChecking no
UserKnownHostsFile /dev/null
User vagrant
LogLevel QUIET

Host proxy.local
StrictHostKeyChecking no
UserKnownHostsFile /dev/null
User vagrant
LogLevel QUIET

Provision with Ansible
=======================
Run: ```ansible-playbook --ask-sudo-pass play.yml```
With parameters: ```ansible-playbook --ask-sudo-pass play.yml```

Getting started
----------------
http://www.ansibleworks.com/docs/intro_getting_started.html

Ansible Modules
----------------
http://www.ansibleworks.com/docs/modules.html

