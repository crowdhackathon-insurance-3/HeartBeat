using System.Collections;
using System.Collections.Generic;
using System.IO;
using System;
using UnityEngine;
using UnityEngine.Networking;

public class RecorderManager : MonoBehaviour
{
    private AudioSource audioSource;
    private static int HEADER_SIZE=44;
    // Start is called before the first frame update
    void Start()
    {
        audioSource = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void RecordFile()
    {
        //audioSource.clip = Microphone.Start("Built-in Microphone", true, 10, 44100);
        Debug.Log(Microphone.devices);
        audioSource.clip = Microphone.Start(null, true, 10, 44100);
        StartCoroutine(StopRecording(5));
    }

    public IEnumerator StopRecording(float _secs)
    {
        yield return new WaitForSeconds(_secs);
        Microphone.End(null);
        audioSource.Play();
        SavWav.Save("myfile", audioSource.clip);
        PostRecording();
    }

    private void PostRecording()
    {
        var samples = new float[audioSource.clip.samples];

        audioSource.clip.GetData(samples, 0);

        Int16[] intData = new Int16[samples.Length];
        //converting in 2 float[] steps to Int16[], //then Int16[] to Byte[]

        Byte[] bytesData = new Byte[samples.Length * 2];
        //bytesData array is twice the size of
        //dataSource array because a float converted in Int16 is 2 bytes.

        StartCoroutine(Upload(Convert.ToBase64String(bytesData)));
        //StartCoroutine(Upload(bytesData));
    }

    IEnumerator Upload(string _rec)
    {
        Debug.Log(_rec);
        List<IMultipartFormSection> formData = new List<IMultipartFormSection>();
        //formData.Add(new MultipartFormDataSection("recording",_rec));
        formData.Add(new MultipartFormDataSection("recording", "Θέλω να φάω πίτσα"));

        UnityWebRequest www = UnityWebRequest.Post("http://172.16.176.206:5000/", formData);
        yield return www.SendWebRequest();

        if (www.isNetworkError || www.isHttpError)
        {
            Debug.Log(www.error);
        }
        else
        {
            Debug.Log("Form upload complete!");
            Debug.Log(www.downloadHandler.text);
            formData.Clear();
            formData.Add(new MultipartFormDataSection("topic", "testTopic"));
            formData.Add(new MultipartFormDataSection("msg", "kardiako"));
            UnityWebRequest www2 = UnityWebRequest.Post("http://172.16.176.206:8090/api/sendMessageToTopic", formData);
            yield return www2.SendWebRequest();

            if (www2.isNetworkError || www2.isHttpError)
            {
                Debug.Log(www2.error);
            }
            else
            {
                Debug.Log("Form2 upload complete!");
                Debug.Log(www2.downloadHandler.text);
            }
        }
    }

    public void SendAccept()
    {
        StartCoroutine(UploadAccept());
    }

    IEnumerator UploadAccept()
    {
        List<IMultipartFormSection> formData = new List<IMultipartFormSection>();
        formData.Add(new MultipartFormDataSection("topic", "testTopic"));
        formData.Add(new MultipartFormDataSection("msg", "kariologos"));
        UnityWebRequest www2 = UnityWebRequest.Post("http://172.16.176.206:8090/api/sendMessageToTopic", formData);
        yield return www2.SendWebRequest();

        if (www2.isNetworkError || www2.isHttpError)
        {
            Debug.Log(www2.error);
        }
        else
        {
            Debug.Log("Form2 upload complete!");
            Debug.Log(www2.downloadHandler.text);
        }
    }
}