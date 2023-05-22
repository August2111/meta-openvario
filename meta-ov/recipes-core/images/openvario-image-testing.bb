require openvario-base-image.bb

# IMAGE_ROOTFS_SIZE ?= "3768320" = 0x39 8000 (+ 0x6 8000 = 0x40 0000)
# IMAGE_ROOTFS_SIZE ?= "1048576" = 0x10 0000
IMAGE_ROOTFS_SIZE ?= "655260"  = 0x0A 0000

IMAGE_INSTALL += "\
    xcsoar \
    xcsoar-menu \
    xcsoar-profiles \
    xcsoar-maps-default \
    sensord-testing\
    variod-testing \
    ovmenu-ng \
"

export IMAGE_BASENAME = "openvario-image-testing"
