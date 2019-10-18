import android.arch.persistence.room.Room
import com.example.mid.db.AppDatabase
import com.example.mid.db.dao.TodoDao
import com.example.mid.db.dao.UserDao
import com.example.mid.db.repositroy.LoginRepository
import com.example.mid.db.repositroy.TodoRepository
import com.example.mid.db.viewModel.LoginViewModel
import com.example.mid.db.viewModel.TodoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(androidContext(),
        AppDatabase::class.java, AppDatabase.DB_NAME).build() }

    single { get<AppDatabase>().getUserDao() }
    single { get<AppDatabase>().getTodoDao() }
}

val repoModule = module {
    single { LoginRepository(get() as UserDao) }
    single { TodoRepository(get() as TodoDao) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get() as LoginRepository) }
    viewModel { TodoViewModel(get() as TodoRepository) }
}