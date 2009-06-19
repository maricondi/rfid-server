class UrlMappings {
  static mappings = {
    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }
    "/rest/$controller/$id?"(action = [GET: "show", POST: "save", PUT: "update", DELETE: "remove"] )
    "/"(view: "/index")
    "500"(view: '/error')
  }
}
