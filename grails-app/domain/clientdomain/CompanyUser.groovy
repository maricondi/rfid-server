package clientdomain

class CompanyUser {
  String userId
  String password
  List<ClientApplication> clientApplications
  static hasMany = [clientApplications: ClientApplication]
}