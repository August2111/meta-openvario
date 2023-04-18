SUMMARY = "Distribution of boot up and recovery itb's with kernel and boot up initramfs built in"
HOMEPAGE = "none"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PR = "r27"

S = "${WORKDIR}/${PN}-${PV}"

SRC_URI = "\
        file://openvario-recovery.its \
        file://zImage.bin \
        file://sun7i-a20-cubieboard2.dtb \
        "


DEPENDS = "\
        dtc-native \
        linux-mainline \
        openvario-recovery-initramfs \
        u-boot-mkimage-native \
        u-boot \
	"

do_compile[deptask] = "do_rm_work"

## the next line can be toggled between new and old!
do_configure () {
	cp ${WORKDIR}/openvario-recovery.its ${S}
	
    #new image
    # dd if=${DEPLOY_DIR_IMAGE}/uImage of=Image bs=64 skip=1
    cp -v ${WORKDIR}/zImage.bin ${S}/Image
	
	# new initramfs
    cp -v ${DEPLOY_DIR_IMAGE}/openvario-base-initramfs-${MACHINE}.cpio.gz ${S}/initramfs.cpio.gz
    
    #new device tree
    cp -v ${WORKDIR}/sun7i-a20-cubieboard2.dtb  ${S}/openvario.dtb
    # new style cp -v ${DEPLOY_DIR_IMAGE}/${MACHINE}.dtb  ${S}/openvario.dtb
    
    
	
	# wrong(aug): cp -v ${DEPLOY_DIR_IMAGE}/uImage ${S}
	# wrong(aug): cp -v ${DEPLOY_DIR_IMAGE}/openvario.dtb ${S}
	#cp -v ${DEPLOY_DIR_IMAGE}/fex.bin ${S}/script.bin
	#if [ ! -e "${S}/zImage-${MACHINE}.bin" ]; then bbfatal "missing uIUmage-${MACHINE}.bin !"; fi
	#if [ ! -e "${S}/magna-initramfs-${MACHINE}.cpio.gz" ]; then bbfatal "missing magna-initramfs-${MACHINE}.cpio.gz !"; fi
	# dd if=${S}/uImage-${MACHINE}.bin of=${S}/zImage skip=64 iflag=skip_bytes
}

### do_configure_old () {
### 	cp ${WORKDIR}/openvario-recovery.its ${S}
### 	#old Image...
###     cp ${WORKDIR}/OldImage.bin ${S}/Image  # only WO
### 	# new initramfs
###     cp -v ${DEPLOY_DIR_IMAGE}/openvario-base-initramfs-${MACHINE}.cpio.gz ${S}/initramfs.cpio.gz
###     #old device tree
###     cp -v ${WORKDIR}/OldDeviceTree.dtb  ${S}/openvario.dtb  # only WO
### }

do_compile () {
    pwd  # only as WO for one action
    # Extract kernel from uImage
    # wrong(aug): dd if=uImage of=Image bs=64 skip=1
    # dumpimage -i uImage -T kernel Image
}

do_mkimage () {
    # Build ITB with provided config
    pwd
    mkimage -A arm -f ${S}/openvario-recovery.its ${S}/ov-recovery.itb
}

addtask mkimage after do_configure before do_install

do_install () {
	cp ${WORKDIR}/${PN}-${PV}/ov-recovery.itb ${DEPLOY_DIR_IMAGE}
}
