package id.semmi.showjokes;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Semmiverian on 2/19/17.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mJokes;

    public JokeAdapter(Context mContext, List<String> mJokes) {
        this.mContext = mContext;
        this.mJokes = mJokes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View skuView = inflater.inflate(R.layout.jokes_rv, parent, false);
        return new ViewHolder(skuView);    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.jokesView.setText(mJokes.get(position));
    }

    @Override
    public int getItemCount() {
        return mJokes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView jokesView;

        ViewHolder(View itemView) {
            super(itemView);
            jokesView = (AppCompatTextView) itemView.findViewById(R.id.jokesText);
        }
    }
}
