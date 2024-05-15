package id.ac.polihasnur.ti.haqi.ujikom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import id.ac.polihasnur.ti.haqi.ujikom.MainList;
import id.ac.polihasnur.ti.haqi.ujikom.R;
import id.ac.polihasnur.ti.haqi.ujikom.dao.MahasiswaDao;
import id.ac.polihasnur.ti.haqi.ujikom.model.Mahasiswa;

public class MahasiswaAdapter extends BaseAdapter implements MahasiswaDao {
    private List<Mahasiswa> mList;
    private Context mContext;

    public MahasiswaAdapter(List<Mahasiswa> mList, MainList mainList) {
        this.mList = mList;
        this.mContext = mainList; // Perbaikan: Menggunakan mainList sebagai Context
    }

    @Override
    public int getCount() {
        return mList.size(); // Perbaikan: Mengembalikan jumlah item dalam mList
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_layout, viewGroup, false);
        TextView tvID = convertView.findViewById(R.id.tv_id);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        tvID.setText(String.valueOf(mList.get(position).getId()));
        tvName.setText(mList.get(position).getNama());
        return convertView;
    }

    @Override
    public void insert(Mahasiswa mahasiswa) {
        mList.add(mahasiswa);
    }

    @Override
    public void update(int id, Mahasiswa mahasiswa) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Mahasiswa getAMahasiswa(int id) {
        return mList.get(id);

    }


    @Override
    public List<Mahasiswa> getAllMahasiswas() {
        return mList;
    }
}
