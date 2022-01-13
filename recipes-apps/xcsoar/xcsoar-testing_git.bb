# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "r13"
RCONFLICTS_${PN}="xcsoar"

SRCREV_pn-xcsoar-testing = "${AUTOREV}" 

SRC_URI = "git://github.com/XCSoar/XCSoar.git;protocol=git;branch=master \
           file://ov-xcsoar.conf"

require xcsoar.inc
