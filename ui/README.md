# Ui

This is the Angular-Project for Microtopes UI.

## Building

The build is done in the docker container - however you can use your normal `ng build --prod` to run your tsc and linter without overhead.
If you want to do it that way, you need to `npm install` first.

## Running

To run this simply start the docker-container.

If not provided another config, it will use the built-in [fallback-config](./src/app/assets/fallback_config.json).

You can also use `docker-compose up`, which should build the image for you and provide it.

## Deployment

To run with the *real* Api there needs to be a config imported into the NGinx-Container. The Config will be read the first time required (e.g. first-time loading the page).
The config file must be under `/usr/share/nginx/html/config/app_config.json` inside the container. Best way to supply this is to mount a volume.
**This convention must be strictly hold!**

The location of the config file is specified in [the environment-file](./src/app/environments/environment.ts) and is *hardcoded* for the built container.
The config will be typechecked and needs to implement [a specified interface](./src/app/models/IConfig.ts).

An example will be provided in a small [docker-compose file](./docker-compose.yml) and inside the meta folder of this project.

## Troubleshooting

### API-URL

Ok, so this maybe looks quite stupid: the api must be exactly the same you are talking to from ui. I had the following problem:

- The UI failed because it could not connect to the API
- Curling the API from the UI-Container was possible
- Invoking the API from localhost was possible
- Invoking the API as www.api.com:3000 was not possible from browser

This is because the localhost your having is not resolving docker-compose hostnames. 
Therefore the api-url needs to be "publicly" reachable, where "publicly" refers to the possibly to just call it from any host.
This means that saying "api_url:{dockercomposeapiurl}" was wrong, and that "api_url:"localhost:3000" worked.

This looks like a bad idea first, but trust me, when working on kubernetes having everything on a different service, this is required. Or atleast I hope so.
If i'm wrong, feel free to mock me via email.
