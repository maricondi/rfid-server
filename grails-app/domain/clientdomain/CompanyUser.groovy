package clientdomain

/**
 * Not quite sure if this is the correct usage, or if this the field of this class should be moved into others
 */
class CompanyUser {
  String userId
  String password
  List<ClientApplication> clientApplications
  static hasMany = [clientApplications: ClientApplication]
}