/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for(int i = 0; i < size; i++)
        {
            storage[i] = null;
        }
        calcSize();
    }

    void save(Resume r) {
        storage[size] = r;
        calcSize();
    }

    Resume get(String uuid) {
        for(int i = 0; i < size; i++)
        {
            if(uuid.equals(storage[i].uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int delPos = -1;
        for(int i = 0; i < size; i++)
        {
            if(uuid.equals(storage[i].uuid))
            {
                delPos = i;
                break;
            }
        }
        for(int i = delPos; i < size; i++)
        {
            storage[i] = storage[i + 1];
        }
        calcSize();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size());
        return result;
    }

    int size() {
        return size;
    }

    void calcSize() {
        for(int i = 0; i < storage.length; i++)
        {
            size = i;
            if(storage[i] == null)
                break;
        }
    }
}
