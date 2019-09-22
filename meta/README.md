# META

This repository contains Meta-Files for the MicroTope project.

Currently that's only the docker compose file, but later this will hold the kubernetes services aswell.

Also I store configurations required to run here and some shared resources for the projects.

## Requirements

- the other microtope images (already build)
- docker-compose 3.3
- docker 19
- Internet connection for vromeros amq image

## Troubleshooting

After i spend a nice afternoon, you may want to `docker system prune` and rebuild everything.

It seems that especially the networks can have very bad sideeffects, or that the docker-compose if not done with docker-compose down is just restarting old containers instead of using fresh images.

This problem was discovered after adding networks to the database and then recieving mariadb errors that the hosts are not allowed to connect.

## About shared_resources

The folder shared-resources contains files which are shared accross the projects.
I would have liked to share them "really" with symbolic links and keep it once, but this breaks docker-logic and can therefore not be used.

On my Machine I established hard links, but those cannot be represented by github.

Therefore, these are only guidelines, until I maybe find a wait to automaticly check whether those files are identical. And add it to the Build-Pipeline.

Some items could be shared via symlinks, but unfortunately I *want* dockerized builds, and I want to lint inside and outside my docker, same goes for e.g. checkstyle.

## Sources

- [Docker vs. Symlinks](https://github.com/moby/moby/issues/1676)
- [Github vs. Hardlinks](https://stackoverflow.com/questions/3729278/git-and-hard-links)
