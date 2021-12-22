require sensord.inc

DESCRIPTION_append = " (Testing)"

PR = "r10"

SRCREV_pn-sensord-testing = "${AUTOREV}"

SRC_URI = "git://github.com/Openvario/sensord.git;protocol=git;branch=master \
            file://sensord.service \
            file://sensord.cfgmgr \              
"

do_install_append() {
    install -d ${D}/opt/conf/default
    install -d ${D}/etc/cfgmgr.d
    install -m 0755 ${S}/compdata ${D}/opt/bin
    install -m 0755 ${S}/sensord.conf ${D}/opt/conf/default/sensord.conf
    install -m 0755 ${WORKDIR}/sensord.cfgmgr ${D}/etc/cfgmgr.d/sensord.cfgmgr
    
}

FILES_${PN}_append = " /opt/bin/compdata \
                    /opt/conf/default/sensord.conf \
                    /etc/cfgmgr.d/sensord.cfgmgr \
"
FILES_${PN}-dev = "/usr/src/debug/sensord-testing/git-r7/git/*"

CONFFILES_${PN} = " /opt/conf/sensord.conf "
