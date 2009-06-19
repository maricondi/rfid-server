class UrlMappings {
  static mappings = {
    "/html/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }
    "/$controller/$id?"(action = [GET: "show", POST: "save", PUT: "update", DELETE: "remove"] )
    "/"(view: "/index")
    "500"(view: '/error')
  }
}
