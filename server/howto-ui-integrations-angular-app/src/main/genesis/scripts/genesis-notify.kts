notify {
    gateways {
        screen("Screen")

        email("Email") {
            smtpHost =  "localhost"
            smtpPort = 587
            smtpUser = "*"
            smtpPw = "*"
            smtpProtocol = TransportStrategy.SMTP
            systemDefaultUserName = "*"
            systemDefaultEmail = "*"
            sendFromUserAddress = false
        }

        teams("Teams")
    }
}
