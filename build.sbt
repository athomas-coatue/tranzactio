import sbt.Keys._

organization := "io.github.gaelrenoux"
name := "tranzactio"
licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
description := "ZIO wrapper for Scala DB libraries (e.g. Doobie)"

scalaVersion := "2.12.12"

scalacOptions ++= Seq(
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",

  "-explaintypes", // Explain type errors in more detail.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.

  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.

  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:explicits", // Warn if an explicit parameter is unused.
  "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports", // Warn when imports are unused.
  "-Ywarn-unused:locals", // Warn if a local definition is unused.
  "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates", // Warn if a private member is unused.
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.

  // "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver. // this is fine
  "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
  "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
  "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
  "-Xlint:private-shadow", //  A private field (or class parameter) shadows a superclass field.
  "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:option-implicit", // Option.apply used implicit view.
  "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
  // "-Xlint:package-object-classes", // Class or object defined in package object. // this is fine
  "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
  "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:unused", // Enable -Ywarn-unused:imports,privates,locals,implicits.
)

addCompilerPlugin("org.scalamacros" % "paradise_2.12.10" % "2.1.0")


val ZioVersion = "1.0.3"
val ZioCatsVersion = "2.2.0.1"
val DoobieVersion = "0.9.2"
val AnormVersion = "2.6.7"

libraryDependencies ++= Seq(
  /* ZIO */
  "dev.zio" %% "zio" % ZioVersion,
  "dev.zio" %% "zio-interop-cats" % ZioCatsVersion,

  /* Doobie */
  "org.tpolecat" %% "doobie-core" % DoobieVersion,
  "org.playframework.anorm" %% "anorm" % AnormVersion % "optional",
)

/* Makes processes is SBT cancelable without closing SBT */
Global / cancelable := true
