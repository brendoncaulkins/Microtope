workflow "Build UI in Docker" {
  on = "pull_request"
  resolves = "buildUI"
}

action "buildUI" {
  uses = "actions/docker/cli@master"
  args = "build ./ui "
}