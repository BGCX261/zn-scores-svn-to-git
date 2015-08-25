package com.zink.ezequiel.Zcores.pro;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ezequiel Zink
 * Date: 17/03/13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class Model {
    private float originalScore;
    private float finalScore;

    public float getScoreReq() {
        return scoreReq;
    }

    private float scoreReq;
    private boolean appproved;

    public Date getCreateDate() {
        return createDate;
    }

    private Date createDate;

    public Model() {
    }

    public float getOriginalScore() {
        return originalScore;
    }

    public float getRequiredScore() {
        return scoreReq;
    }

    public void setScore(float orig, int dec, float idealScore, float maxApprob, float minApprob, float reqPercentage) {
        float fdec = 10^dec;
        this.scoreReq = Math.round(((idealScore * reqPercentage)/ 100.0f)*fdec)/fdec;
        this.originalScore = orig;
        if(orig == scoreReq){
            this.finalScore = minApprob;
            this.appproved = true;
        } else if (orig > scoreReq) {
            this.finalScore = (((maxApprob-minApprob)/(idealScore-scoreReq))*(orig-scoreReq))+minApprob;
            this.appproved = true;
        } else {
            this.finalScore = (((minApprob-1)/scoreReq)*orig)+1;
            this.appproved = false;
        }
        this.createDate = new Date();
        this.finalScore = Math.round((this.finalScore*fdec)/fdec);
    }

    public float getFinalScore() {
        return finalScore;
    }

    public boolean isAppproved() {
        return appproved;
    }

}
