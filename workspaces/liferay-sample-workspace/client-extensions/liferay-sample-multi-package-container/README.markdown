# liferay-sample-multi-package-container

This is project that contains multiple client extensions that share a single container but have separate build processes.

## Client Extensions Included

- custom-element-cra (Custom Element, generated with create-react-app)
- custom-element-cra-ejected (Custom Element, generated with create-react-app but then [ejected](https://create-react-app.dev/docs/available-scripts#npm-run-eject) so that we can configure webpack for `externals`.)
- global-css (Global CSS)
- fds-cell(FDS Cell Renderer)

## Shared Resources

This project also contains shared resources and dependencies. These dependencies are bundled and provided as independent resources.

> Note: Unfortunately due to create-react-app's lack of configuration, we are not able to share dependencies to `custom-element-cra`. If we wanted to consume a shared dependency in that client extension, we would need to utilize the "eject" feature of create react app and manually configure webpack to use `externals`.

- `underscore`(npm module)
- `shared-utils`(code in `./src/shared-utils`)
