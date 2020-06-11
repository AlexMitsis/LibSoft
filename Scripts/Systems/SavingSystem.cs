using System.IO;
using UnityEngine;
using System.Runtime.Serialization.Formatters.Binary;

public static class SavingSystem
{
    #region Saving Functions:
    public static void SaveMemberData()
    {
        BinaryFormatter bF = new BinaryFormatter();

        string path = Application.persistentDataPath + "/" + AccountManager.currentAccount.username + "_memberData.sav";

        FileStream stream = new FileStream(path, FileMode.Create);

        SavedMemberData data = new SavedMemberData();

        bF.Serialize(stream, data);
        stream.Close();
    }

    public static void SaveSystemData()
    {
        BinaryFormatter bF = new BinaryFormatter();

        string path = Application.persistentDataPath + "systemData.sav";

        FileStream stream = new FileStream(path, FileMode.Create);

        SavedSystemData data = new SavedSystemData();

        bF.Serialize(stream, data);
        stream.Close();
    }

    /*public static void SaveBookComments()
    {
        BinaryFormatter bF = new BinaryFormatter();

        string path = Application.persistentDataPath + "bookComments.sav";

        FileStream stream = new FileStream(path, FileMode.Create);

        BookCommentsData data = new BookCommentsData();

        bF.Serialize(stream, data);
        stream.Close();
    }*/
    #endregion

    #region Loading Functions:
    public static SavedMemberData LoadMemberData(Member memberToLoad)
    {
        string path = Application.persistentDataPath + "/" + memberToLoad.username + "_memberData.sav";

        if (File.Exists(path))
        {
            BinaryFormatter bF = new BinaryFormatter();
            FileStream stream = new FileStream(path, FileMode.Open);

            SavedMemberData data = bF.Deserialize(stream) as SavedMemberData;
            stream.Close();

            return data;
        }
        else
        {
            Debug.Log("Save file for user " + AccountManager.currentAccount.username + " could not be found.");
            return null;
        }
    }

    public static SavedSystemData LoadSystemData()
    {
        string path = Application.persistentDataPath + "systemData.sav";

        if (File.Exists(path))
        {
            BinaryFormatter bF = new BinaryFormatter();
            FileStream stream = new FileStream(path, FileMode.Open);

            SavedSystemData data = bF.Deserialize(stream) as SavedSystemData;
            stream.Close();

            return data;
        }
        else
        {
            Debug.Log("Save file for system data could not be found.");
            return null;
        }
    }

    /*public static BookCommentsData LoadBookComments()
    {
        string path = Application.persistentDataPath + "bookComments.sav";

        if (File.Exists(path))
        {
            BinaryFormatter bF = new BinaryFormatter();
            FileStream stream = new FileStream(path, FileMode.Open);

            BookCommentsData data = bF.Deserialize(stream) as BookCommentsData;
            stream.Close();

            return data;
        }
        else
        {
            Debug.Log("Save file for books comments could not be found.");
            return null;
        }
    }*/
    #endregion
}
