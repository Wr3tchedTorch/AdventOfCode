using System.Collections;
using System.Text.RegularExpressions;

namespace Day9;

class Oasis
{
    private ArrayList _fileLines;
    public Oasis(String filePath)
    {
        _fileLines = Utils.GetLinesFromFile(filePath);
    }

    private List<int> GetDiferenceList(List<int> numbersList)
    {
        List<int> diferenceSequence = new List<int>();
        for (int i = 1; i < numbersList.Count; i++)
        {
            diferenceSequence.Add(numbersList[i] - numbersList[i - 1]);
        }

        return diferenceSequence;
    }

    private List<List<int>> GetFullSequence(string sequence)    
    {
        List<List<int>> newList = new List<List<int>>();
        Regex removeMultipleSpace = new Regex("[ ]{2,}");
        removeMultipleSpace.Replace(sequence, " ");

        List<int> sequenceNumbers = new List<int>();
        foreach (string num in sequence.Split(" "))
        {
            sequenceNumbers.Add(Int32.Parse(num));
        }
        
        newList.Add(sequenceNumbers);
        newList.Add(GetDiferenceList(sequenceNumbers));
        bool reachedEnd = true;        
        int i = newList.Count-1;
        do
        {
            newList.Add(GetDiferenceList(newList[i]));
            reachedEnd = true;
            foreach (int num in newList[i+1])
            {
                if (num != 0)
                {
                    reachedEnd = false;
                    break;
                }
            }
            i++;
        } while (!reachedEnd);        

        return newList;
    }

    private int GetExtrapolatedNum(string sequence) {
        List<List<int>> fullSequence =  GetFullSequence(sequence);
        
        int numOfLines = fullSequence.Count-1;
        int lastNumOfLastLine = fullSequence[numOfLines][fullSequence[numOfLines].Count-1];
        int numberAboveLeft = fullSequence[numOfLines-1][fullSequence[numOfLines-1].Count-2];
        int extrapolatedNum = lastNumOfLastLine + numberAboveLeft;
        for (int i = fullSequence.Count-3; i >= 0; i--)
        {
            int leftNumber = fullSequence[i][fullSequence[i].Count-1];
            extrapolatedNum = leftNumber + extrapolatedNum;
        }
        return extrapolatedNum;
    }
    public int GetExtrapolatedSum()
    {
        int sum = 0;
        foreach (string line in _fileLines)
        {
            sum += GetExtrapolatedNum(line);
        }
        return sum;
    }
}