package com.kaleidosoftlabs.hsm.api.common


/**
 * Routes and controller name mapping for URLs
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 5:50 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
object Routes {
  final var all: String = "all"
  final var get: String = "get"
  final var create: String = "create"
  final var update: String = "update"
  final var delete: String = "delete"
  final var testController: String = "test"
  final var errorController: String = "errors"
  final var userController: String = "users"
  final var userVerify: String = "verify"
  final var geoFenceController: String = "fences"
  final var locationController: String = "locations"
  final var trailController: String = "trails"
  final var tripController: String = "trips"
  final var routeController: String = "routes"
  final var cPth: String = "/hsm"
  final var sPth: String = "/api"
  final var credsMissing: String = "credsMissing"
  final var authFailed: String = "authFailed"
  final var credsMissingUrl: String = cPth + sPth + "/errors/credsMissing"
  final var authFailedUrl: String = cPth + sPth + "/errors/authFailed"
  final var userAuthRoutes: List[String] = List(
    cPth + sPth + "/test/authUser.json",
    cPth + sPth + "/test/authUser.xml",
    cPth + sPth + "/test/authUser",
    cPth + sPth + "/" + userController + "/verify.json",
    cPth + sPth + "/" + userController + "/verify.xml",
    cPth + sPth + "/" + userController + "/verify"
  )
}