package clientdomain

import clientdomain.ClientApplication

class CompanyUser {
  String userId
  String password
  List<ClientApplication> clientApplications
  static hasMany = [clientApplications: ClientApplication]
}