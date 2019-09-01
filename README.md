# META

This repository contains Meta-Files for the MicroTope project.

Currently that's only the docker compose file, but later this will hold the kubernetes services aswell.

## Requirements

- the other microtope images (already build)
- docker-compose 3.3
- docker 18
- Internet connection for vromeros amq image

## Troubleshooting

After i spend a nice afternoon, you may want to `docker system prune` and rebuild everything.

It seems that especially the networks can have very bad sideeffects, or that the docker-compose if not done with docker-compose down is just restarting old containers instead of using fresh images.

This problem was discovered after adding networks to the database and then recieving mariadb errors that the hosts are not allowed to connect.

## Addendum

This repo will hold v. 0.1 for most of my components very (very) long. This is as I don't want to change my stuff in multiple repos that frequently.

I've chosen to have multiple repos to play with multiple ci/cd pipelines and not to have a monorepo. This will maybe proof bad, but I want to collect some experiences and test things out.
