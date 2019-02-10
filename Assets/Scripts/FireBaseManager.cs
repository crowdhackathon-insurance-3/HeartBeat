using UnityEngine;
using UnityEngine.UI;
using Firebase.Messaging;

public class FireBaseManager : MonoBehaviour
{
    public Text textUser;
    public GameObject docPanel;

    public void Start()
    {
        FirebaseMessaging.TokenReceived += OnTokenReceived;
        FirebaseMessaging.MessageReceived += OnMessageReceived;
        FirebaseMessaging.SubscribeAsync("testTopic");
    }

    public void OnTokenReceived(object sender, TokenReceivedEventArgs token)
    {
        Debug.Log("Received Registration Token: " + token.Token);
    }

    public void OnMessageReceived(object sender, MessageReceivedEventArgs e)
    {
        Debug.Log("Received a new message from: " + e.Message.From);
        Debug.Log("Received a new message from: " + e.Message.Data);
        //Debug.Log("Received a new message from: " + e.Message.);
        textUser.gameObject.SetActive(true);
        textUser.text = e.Message.Data["test"]; 

        if (textUser!=null && e.Message.Data["test"]=="kariologos")
        {
            textUser.gameObject.SetActive(true);
            textUser.text = "Έρχεται ο καρδιολόγος Γιαννής Μαραγκός";
        }

        if (textUser != null && e.Message.Data["test"] == "kardiako")
        {
            docPanel.SetActive(true);
        }

    }

}
