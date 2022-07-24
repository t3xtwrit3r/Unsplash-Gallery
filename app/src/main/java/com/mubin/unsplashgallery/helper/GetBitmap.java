package com.mubin.unsplashgallery.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.ByteArrayOutputStream;

public class GetBitmap {

    public void shareImage(String title, String photoLink, Context context){
        Picasso.get().load(photoLink).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri imageUri = getImageUri(context, title, bitmap);
                sharingIntent.setType("image/png");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(sharingIntent);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public Uri getImageUri(Context context, String title, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, title, null);
        return Uri.parse(path);
    }

}
