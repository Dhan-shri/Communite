package com.dhanshri.community.data.loginpage

data class LoginUIState (

    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,

)