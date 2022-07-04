name := "src-dirs"

lazy val Step1 = config("Step1") extend(Compile)
lazy val step1Settings: Seq[Setting[_]] =
  inConfig(Step1)(
    Seq(
      Step1 / target := (Compile / target).value.getParentFile / "src-target",
      Step1 / crossTarget := (Step1 / target).value / (Compile / crossTarget).value.name,
      Step1 / unmanagedSourceDirectories += (Compile / baseDirectory).value / "step1",
      Step1 / classDirectory := (Step1 / crossTarget).value / "step1"
    )
  )

lazy val Step2 = config("Step2") extend(Compile)
lazy val step2Settings: Seq[Setting[_]] =
  inConfig(Step1)(
    Seq(
      Step2 / target := (Compile / target).value.getParentFile / "src-target",
      Step2 / crossTarget := (Step2 / target).value / (Compile / crossTarget).value.name,
      Step2 / unmanagedSourceDirectories += (Compile / baseDirectory).value / "step2",
      Step2 / classDirectory := (Step2 / crossTarget).value / "step2"
    )
  )

val exampleProject = (project in file("."))
  .configs(Seq(Step1, Step2) : _*)
  .settings(step1Settings ++ step2Settings)
