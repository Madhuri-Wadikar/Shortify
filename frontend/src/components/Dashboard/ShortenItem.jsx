import dayjs from 'dayjs';
import React, { useEffect, useState } from 'react';
import CopyToClipboard from 'react-copy-to-clipboard';
import { FaExternalLinkAlt, FaRegCalendarAlt } from 'react-icons/fa';
import { IoCopy } from 'react-icons/io5';
import { LiaCheckSolid } from 'react-icons/lia';
import { MdAnalytics, MdOutlineAdsClick } from 'react-icons/md';
import api from '../../api/api';
import { Link, useNavigate } from 'react-router-dom';
import { useStoreContext } from '../../contextApi/ContextApi';
import { Hourglass } from 'react-loader-spinner';
import Graph from './Graph';

const ShortenItem = ({ originalUrl, shortUrl, clickCount, createdDate }) => {
  const { token } = useStoreContext();
  const navigate = useNavigate();

  const [isCopied, setIsCopied] = useState(false);
  const [analyticToggle, setAnalyticToggle] = useState(false);
  const [loader, setLoader] = useState(false);
  const [selectedUrl, setSelectedUrl] = useState("");
  const [analyticsData, setAnalyticsData] = useState([]);

 
  const baseUrl = import.meta.env.VITE_REACT_FRONT_END_URL || "";
  const subDomain = baseUrl.replace(/^https?:\/\//, "");

  const analyticsHandler = (shortUrl) => {
    if (!analyticToggle) {
      setSelectedUrl(shortUrl);
    }
    setAnalyticToggle(!analyticToggle);
  };

  const fetchMyShortUrl = async () => {
    setLoader(true);
    try {
      const { data } = await api.get(
        `/api/urls/analytics/${selectedUrl}?startDate=2026-01-01T00:00:00&endDate=2026-12-31T23:59:59`,
        {
          headers: {
            Authorization: "Bearer " + token,
          },
        }
      );
      setAnalyticsData(data);
      setSelectedUrl("");
    } catch (error) {
      console.log(error);
      navigate("/error");
    } finally {
      setLoader(false);
    }
  };

  useEffect(() => {
    if (selectedUrl) {
      fetchMyShortUrl();
    }
  }, [selectedUrl]);

  return (
    <div className="bg-slate-100 shadow-lg border border-dotted border-slate-500 px-6 py-3 rounded-md">

      <div className="flex sm:flex-row flex-col justify-between gap-5 py-5">

        
        <div className="flex-1 overflow-x-auto">

          {/* SHORT LINK */}
          <div className="flex items-center gap-2">
            <Link
              to={`/s/${shortUrl}`}   
              target="_blank"
              className="text-[17px] font-semibold text-linkColor"
            >
              {subDomain + "/s/" + shortUrl}
            </Link>
            <FaExternalLinkAlt className="text-linkColor" />
          </div>

          {/* ORIGINAL URL */}
          <div className="mt-2">
            <h3 className="text-slate-700 text-[17px]">
              {originalUrl}
            </h3>
          </div>

          {/* STATS */}
          <div className="flex items-center gap-8 pt-6">

            <div className="flex items-center font-semibold text-green-800 gap-1">
              <MdOutlineAdsClick className="text-[22px]" />
              <span>{clickCount}</span>
              <span>{clickCount <= 1 ? "Click" : "Clicks"}</span>
            </div>

            <div className="flex items-center gap-2 font-semibold text-slate-800">
              <FaRegCalendarAlt />
              <span>
                {dayjs(createdDate).format("MMM DD, YYYY")}
              </span>
            </div>

          </div>
        </div>

    
        <div className="flex items-center gap-4">

          {/* COPY BUTTON */}
          <CopyToClipboard
            text={`${baseUrl}/s/${shortUrl}`}   
            onCopy={() => setIsCopied(true)}
          >
            <div className="flex cursor-pointer gap-1 items-center bg-gradient-to-r from-blue-500 to-purple-600 py-2 px-6 rounded-md text-white">
              <button>{isCopied ? "Copied" : "Copy"}</button>
              {isCopied ? <LiaCheckSolid /> : <IoCopy />}
            </div>
          </CopyToClipboard>

          {/* ANALYTICS BUTTON */}
          <div
            onClick={() => analyticsHandler(shortUrl)}
            className="flex cursor-pointer gap-1 items-center bg-rose-700 py-2 px-6 rounded-md text-white"
          >
            <button>Analytics</button>
            <MdAnalytics />
          </div>

        </div>
      </div>

      {/* ANALYTICS SECTION */}
      {analyticToggle && (
        <div className="max-h-96 min-h-96 border-t-2 mt-5">

          {loader ? (
            <div className="flex justify-center items-center h-40">
              <Hourglass visible height="50" width="50" />
            </div>
          ) : (
            <>
              {analyticsData.length === 0 && (
                <div className="text-center mt-10">
                  <h1 className="font-bold text-lg">
                    No Data For This Time Period
                  </h1>
                </div>
              )}

              {analyticsData.length > 0 && (
                <Graph graphData={analyticsData} />
              )}
            </>
          )}

        </div>
      )}

    </div>
  );
};

export default ShortenItem;