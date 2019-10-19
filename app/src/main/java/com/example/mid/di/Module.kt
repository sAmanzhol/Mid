import android.arch.persistence.room.Room
import com.example.mid.db.AppDatabase
import com.example.mid.db.dao.FactDao
import com.example.mid.db.dao.UserDao
import com.example.mid.db.repositroy.FactRepositoryDB
import com.example.mid.db.repositroy.FactRepositoryWeb
import com.example.mid.db.repositroy.IFactRepository
import com.example.mid.db.repositroy.LoginRepository
import com.example.mid.db.viewModel.LoginViewModel
import com.example.mid.db.viewModel.FactViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(androidContext(),
        AppDatabase::class.java, AppDatabase.DB_NAME).build() }

    single { get<AppDatabase>().getUserDao() }
    single { get<AppDatabase>().getTodoDao() }
}

val repoModule = module {
    single { LoginRepository(get() as UserDao) }
    single<FactRepositoryWeb> { FactRepositoryWeb(get() as FactDao) }
    single<FactRepositoryDB> { FactRepositoryDB(get() as FactDao) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get() as LoginRepository) }
    viewModel(named("local")) { FactViewModel(get() as FactRepositoryDB) }
    viewModel(named("remote")) { FactViewModel(get() as FactRepositoryWeb) }
}