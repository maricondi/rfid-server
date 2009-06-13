class UrlMappings {
  static mappings = {
    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }
    "/rest/rfidCard/$id?"(controller:"restRfidCard", action = [GET: "show", POST: "save", PUT: "update", DELETE: "remove"] )
    "/"(view: "/index")
    "500"(view: '/error')
  }
}
