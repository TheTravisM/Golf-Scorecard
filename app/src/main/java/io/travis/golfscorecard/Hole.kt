package io.travis.golfscorecard

class Hole {
    private var mLabel: String
    private var mStrokeCount: Int


    constructor(label: String, strokeCount:Int) {
        mLabel = label
        mStrokeCount = strokeCount
    }

    fun getLabel(): String{
        return mLabel
    }

    fun setLabel(label:String){
        this.mLabel = label
    }

    fun getStrokeCount(): Int{
        return mStrokeCount
    }

    fun setStrokeCount(strokeCount: Int) {
        this.mStrokeCount = strokeCount
    }

}