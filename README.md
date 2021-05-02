# Shepherd

Shepherd is a collaborative tool that aims to bring together a community of
people willing to promote trustworthy and objective information.

## Installation

> Note: the following instructions are for running the whole stack. Please note
> that each layers can be run independently using docker or your preferred local
> tools

### Services

Installation is seamless on your machine, thanks to Docker:

```shell
~$ git clone https://github.com/pbouillon/Shepherd
~$ cd Shepherd
~$ docker-compose up
```

Wait for the images to be downloaded and built and head down to the following URLs:

- http://localhost:4200 for the **front end**
- http://localhost:8080/swagger-ui/#/ for the **back end** (more specifically for the API interactive documentation)

> Note: if you want to access the RabbitMQ control panel once the stack is
> running, you can reach it on http://localhost:15672/ using the default
> credentials: `guest` / `guest`

#### Inject data

If you want to inject data to populate the app, execute the following steps:

```shell
~$ cd Shepherd/tools/seeding
~$ pip install -r requirements.txt
~$ python seeder.py
```

> Note: this script is using Python 3.7+

### Browser extension

> Note: the extension is only available on Chrome / Chromium browsers

Since the extension is not on the store, the installation has to be made using
the developer features

1. From your settings, go to the extension panel
1. Enable the developer mode
1. Click on "Load an unpacked extension
1. Select the folder: `~/Shepherd/extension/shepherd` (be sure to use the right prefix)
1. The extension should now appear among the other ones that you may already have
