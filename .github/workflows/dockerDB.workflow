workflow "Build DB Docker Image" {
  on = "pull_request"
  resolves = "buildDB"
}

action "buildDB" {
  uses = "actions/docker/cli@master"
  args = "build ./database "
}