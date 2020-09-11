package xyz.luchengeng.authentication.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

enum class ApplierType{
    INDIVIDUAL,
    ENTERPRISE
}
@Entity
 abstract class ApplierInfo(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
                            val name : String,
                            val type : ApplierType,
                            val tel : String,
                            val email : String,
                            val fax : String?,
                            val zipCode : String?,
                            val contactAddress: String
                            )
@Entity
class IndividualApplierInfo(name : String,
                                 type : ApplierType,
                                 tel : String,
                                 email : String,
                                 fax : String?,
                                 zipCode : String?,
                                 contactAddress: String,
                                 val citizenId : String
) : ApplierInfo(null,name, type, tel, email, fax, zipCode, contactAddress){
    companion object{
        fun sysUserFromName(name : String) = IndividualApplierInfo(name,ApplierType.INDIVIDUAL,"","","","","","")
    }
}

@Entity
class EnterpriseApplierInfo(name : String,
                            type : ApplierType,
                            tel : String,
                            email : String,
                            fax : String?,
                            zipCode : String?,
                            contactAddress: String,
                            val licenseId : String,
                            val registrationAddress : String,
                            val legalPerson : String,
                            val legalPersonPost : String,
                            val registrationCapital : Double,
                            val contact : String,
                            val contactPost : String
) : ApplierInfo(null,name, type, tel, email, fax, zipCode, contactAddress)

