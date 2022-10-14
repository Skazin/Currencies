object AppVersion {
    internal const val versionCode = 1

    internal const val stage: String = Stage.Release.tag
    internal const val majorVersion = 0
    internal const val minorVersion = 0
    internal const val patchVersion = 1
}

sealed class Stage {

    @Suppress("unused")
    object Alpha : Stage() {
        const val tag = ALPHA_TAG
    }

    @Suppress("unused")
    object Beta : Stage() {
        const val tag = BETA_TAG
    }

    @Suppress("unused")
    object ReleaseCandidate : Stage() {
        const val tag = RELEASE_CANDIDATE_TAG
    }

    object Release : Stage() {
        const val tag = RELEASE_TAG
    }

    companion object {
        const val ALPHA_TAG = "-a"
        const val BETA_TAG = "-b"
        const val RELEASE_CANDIDATE_TAG = "-rc"
        const val RELEASE_TAG = ""
    }
}