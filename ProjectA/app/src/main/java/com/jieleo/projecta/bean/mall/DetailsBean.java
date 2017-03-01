package com.jieleo.projecta.bean.mall;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jie on 2017/3/1.
 */

public class DetailsBean  implements Parcelable {

        private String cover_image_url;
        private String cover_webp_url;
        private String id;
        private int merchant_type;
        private String short_description;
        private String title;
        private List<DetailsBean.SkusBean> skus;


    protected DetailsBean(Parcel in) {
        cover_image_url = in.readString();
        cover_webp_url = in.readString();
        id = in.readString();
        merchant_type = in.readInt();
        short_description = in.readString();
        title = in.readString();
        skus = in.createTypedArrayList(SkusBean.CREATOR);
    }

    public static final Creator<DetailsBean> CREATOR = new Creator<DetailsBean>() {
        @Override
        public DetailsBean createFromParcel(Parcel in) {
            return new DetailsBean(in);
        }

        @Override
        public DetailsBean[] newArray(int size) {
            return new DetailsBean[size];
        }
    };

    public String getCover_image_url() {
            return cover_image_url;
        }

        public void setCover_image_url(String cover_image_url) {
            this.cover_image_url = cover_image_url;
        }

        public String getCover_webp_url() {
            return cover_webp_url;
        }

        public void setCover_webp_url(String cover_webp_url) {
            this.cover_webp_url = cover_webp_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMerchant_type() {
            return merchant_type;
        }

        public void setMerchant_type(int merchant_type) {
            this.merchant_type = merchant_type;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


    public List<SkusBean> getSkus() {
        return skus;
    }

    public void setSkus(List<SkusBean> skus) {
        this.skus = skus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cover_image_url);
        dest.writeString(cover_webp_url);
        dest.writeString(id);
        dest.writeInt(merchant_type);
        dest.writeString(short_description);
        dest.writeString(title);
        dest.writeTypedList(skus);
    }


    public static class SkusBean implements Parcelable{
            /**
             * cover_image_url : null
             * fixed_price : 51.00
             * id : 2140
             * item_id : 101488
             * price : 52.00
             * sold : 0
             * specs : [{"property":"均码","title":"规格"}]
             * stock : 500
             * supply_price : 40.80
             */

            private Object cover_image_url;
            private String fixed_price;
            private int id;
            private int item_id;
            private String price;
            private int sold;
            private int stock;
            private String supply_price;
            private List<MallBodyBean.DataBean.ItemsBean.SkusBean.SpecsBean> specs;

            protected SkusBean(Parcel in) {
                fixed_price = in.readString();
                id = in.readInt();
                item_id = in.readInt();
                price = in.readString();
                sold = in.readInt();
                stock = in.readInt();
                supply_price = in.readString();
                specs = in.createTypedArrayList(MallBodyBean.DataBean.ItemsBean.SkusBean.SpecsBean.CREATOR);
            }


            public static final Creator<SkusBean> CREATOR = new Creator<SkusBean>() {
                @Override
                public SkusBean createFromParcel(Parcel in) {
                    return new SkusBean(in);
                }

                @Override
                public SkusBean[] newArray(int size) {
                    return new SkusBean[size];
                }
            };

            public Object getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(Object cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public String getFixed_price() {
                return fixed_price;
            }

            public void setFixed_price(String fixed_price) {
                this.fixed_price = fixed_price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getSold() {
                return sold;
            }

            public void setSold(int sold) {
                this.sold = sold;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getSupply_price() {
                return supply_price;
            }

            public void setSupply_price(String supply_price) {
                this.supply_price = supply_price;
            }

            public List<MallBodyBean.DataBean.ItemsBean.SkusBean.SpecsBean> getSpecs() {
                return specs;
            }

            public void setSpecs(List<MallBodyBean.DataBean.ItemsBean.SkusBean.SpecsBean> specs) {
                this.specs = specs;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(fixed_price);
                dest.writeInt(id);
                dest.writeInt(item_id);
                dest.writeString(price);
                dest.writeInt(sold);
                dest.writeInt(stock);
                dest.writeString(supply_price);
                dest.writeTypedList(specs);
            }


            public static class SpecsBean implements Parcelable{
                /**
                 * property : 均码
                 * title : 规格
                 */

                private String property;
                private String title;

                protected SpecsBean(Parcel in) {
                    property = in.readString();
                    title = in.readString();
                }


                public static final Creator<SpecsBean> CREATOR = new Creator<SpecsBean>() {
                    @Override
                    public SpecsBean createFromParcel(Parcel in) {
                        return new SpecsBean(in);
                    }

                    @Override
                    public SpecsBean[] newArray(int size) {
                        return new SpecsBean[size];
                    }
                };

                public String getProperty() {
                    return property;
                }

                public void setProperty(String property) {
                    this.property = property;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }


                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(property);
                    dest.writeString(title);
                }
            }
            }
        }


