workflow "Build API in Docker" {
  on = "pull_request"
  resolves = "buildAPI"
}

action "buildAPI" {
  uses = "actions/docker/cli@master"
  args = "build ./api "
}