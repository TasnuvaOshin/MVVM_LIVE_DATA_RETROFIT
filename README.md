# Model View View Model with Live Data Using Retrofit Network Call
> How You can use mvvm for live data and retofit its quite easy follow these steps hope this will help you . You can get Updated Value From the Server via  Network call .For More CHeck SampleActivity.class

সারভার থেকে লাইভ ডাটা অবজাভ করে এপর ভিউতে কিভাবে খুব ইজিলি উপডেট করতে পারবেন এটাই দেখানো হল ঃ  এখানে কিছু স্যাম্পল দেয়া হল 
বিস্তারিত    জানতে আপনি    SampleActivity  চেক করুন। 

![](header.png)

## Dependency

Android Studio:build.gradle(module app)

```sh
  def lifecycle_version = "2.2.0"
    implementation "android.arch.lifecycle:extensions:$lifecycle_version"
    annotationProcessor "android.arch.lifecycle:compiler:$lifecycle_version"
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
```

Android Studio:build.gradle(Project):

```sh

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

```

## Usage example

We Need 
1. Repository (From Where Will Fetch the date think Like an Garments/factory which will produce item here which will fetch data from the server)
2. ViewModel  (From Where User Will See the Data , Like and Shop Which You show the user product that comes from Garments/factory,Shop Will Decide How User will gona show it) 
3. Model (Data Structure that will show which type of data should repository product and structure )
4. API Interface ( For Server-Client Network Relationship)
5. Connection ( For API Interface Estrablishment)

## Development - 1st Connection 

For Any Network We need Connection : Here You can make Connection .  

```sh
public class apiConnection {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apzo.xyz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}

```



## Development - 2nd API Interface 

We Need API Interface to END to END Point Connection and Method 

```sh

public interface apiClient {
   

    @GET("test/get.php")
    Call<ArrayList<resultModel>> getResult();

}


```


## Development - 3rd Model  

We Need To Define What type of Model Should Repository Follow to Produce Item/data  

```sh
public class resultModel {
    String id;
    String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public resultModel() {
    }

    public resultModel(String id, String value) {
        this.id = id;
        this.value = value;
    }
}



```


## Development - 4th Repository 

How repositoy Will Product the data From The Server and How it will Supply to the ViewModel(Shop)

```sh

public class resultRepository {

    apiClient apiClient;
    private static resultRepository repository;
    MutableLiveData<ArrayList<resultModel>> data = new MutableLiveData<>();


    public static resultRepository getInstance() {
        if (repository == null) {
            repository = new resultRepository();

        }
        return repository;
    }

    public MutableLiveData<ArrayList<resultModel>> getResult() {
        return data;
    }


    public void fetchresult() {
        apiClient = apiConnection.cteateService(apiClient.class);
        apiClient.getResult().enqueue(new Callback<ArrayList<resultModel>>() {
            @Override
            public void onResponse(Call<ArrayList<resultModel>> call, Response<ArrayList<resultModel>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<resultModel>> call, Throwable t) {

            }
        });
    }
}



```


## Development - 5th ViewModel 

ViewModel Will Get Data From the Repository and Ready to Show it to the Customer/Client Side 

```sh

public class resultViewModel extends AndroidViewModel implements LifecycleObserver {
    MutableLiveData<ArrayList<resultModel>> list;
    resultRepository repository;

    public resultViewModel(@NonNull Application application) {
        super(application);

        if(list != null){
            return;
        }
        repository = repository.getInstance();
        repository.fetchresult();
        list = repository.getResult();
    }

    public MutableLiveData<ArrayList<resultModel>> getList() {
        return list;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void checkData(){
        repository.getResult();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void checkDataResume(){
        repository.getResult();
    }
}



```


## Development - 6th User End 

How Will Show the Data , UI Update and show 

```sh

public class SampleActivity extends AppCompatActivity {
    private TextView result;
    resultViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        result = findViewById(R.id.result);
        viewModel = ViewModelProviders.of(this).get(resultViewModel.class);
        getLifecycle().addObserver(viewModel);
        viewModel.getList().observe(this, new Observer<ArrayList<resultModel>>() {
            @Override
            public void onChanged(ArrayList<resultModel> resultModels) {
                   if(resultModels.size() > 0){
                       result.setText(resultModels.get(0).getValue());
                   }
            }
        });

        /*
        if you want to check data after 15s interval you can simply run a handler wich will check the update if
        and update found our mainview will automatically update
         */
//        final Handler handler = new Handler();
//        final int delay = 150000; //milliseconds
//
//        handler.postDelayed(new Runnable(){
//            public void run(){
//                resultRepository.getInstance().fetchresult();
//                handler.postDelayed(this, delay);
//            }
//        }, delay);

    }


}

```




## Meta

Tasnuva Tabassum Oshin – [@tasnuvaoshin](https://twitter.com/tasnuvaoshin) – tasnuva.oshin12@gmail.com



