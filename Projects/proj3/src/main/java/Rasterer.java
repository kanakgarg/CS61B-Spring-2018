import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
                                ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;
    double rullon;
    double rlrlon;
    double rullat;
    double rlrlat;
    int depth;
    boolean querySuccess;
    String[][] rgrind;

    double ullon;
    double ullat;
    double lrlon;
    double lrlat;
    double width;
    double height;

    int startLon, endLon, startLat, endLat;

    public Rasterer() {
        rullon = 0;
        rlrlon = 0;
        rullat = 0;
        rlrlat = 0;
        depth = 0;
        querySuccess = true;
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        ullon = params.get("ullon");
        lrlon = params.get("lrlon");
        ullat = params.get("ullat");
        lrlat = params.get("lrlat");
        width = params.get("w");
        height = params.get("h");

        double lonDPP = (lrlon - ullon) / width;

        depth = 0;
        while ((ROOT_LRLON - ROOT_ULLON) / Math.pow(2, depth + 8) > lonDPP) {
            depth++;
        }

        if (depth > 7) {
            depth = 7;
        }

        double diffLon =  (ROOT_LRLON - ROOT_ULLON) / Math.pow(2, depth);
        double diffLat =  (ROOT_LRLAT - ROOT_ULLAT) / Math.pow(2, depth);

        if (!correctVal()) {
            querySuccess = false;
        }



        startLon = (int) ((ullon - ROOT_ULLON) / diffLon);
        rullon = ROOT_ULLON + diffLon * startLon;
        endLon = (int) ((lrlon - ROOT_ULLON) / diffLon) + 1;
        rlrlon = ROOT_ULLON + diffLon * endLon;
        startLat = (int) ((ullat - ROOT_ULLAT) / diffLat);
        rullat = ROOT_ULLAT + diffLat * startLat;
        endLat = (int) ((lrlat - ROOT_ULLAT) / diffLat) + 1;
        rlrlat = ROOT_ULLAT + diffLat * endLat;

        if (lrlon == ROOT_LRLON) {
            endLon -= 1;
            rlrlon = ROOT_LRLON;
        }

        rgrind = new String[endLat - startLat][endLon - startLon];
        for (int j = startLat; j < endLat; j++) {
            for (int k = startLon; k < endLon; k++) {
                rgrind[j - startLat][k - startLon] = "d" + depth + "_x" + k + "_y" + j + ".png";
            }
        }

        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", rgrind);
        results.put("raster_ul_lon", rullon);
        results.put("raster_ul_lat", rullat);
        results.put("raster_lr_lon", rlrlon);
        results.put("raster_lr_lat", rlrlat);
        results.put("depth", depth);
        results.put("query_success", querySuccess);
        return results;
    }

    public boolean correctVal() {
        if (lrlat > ullat || lrlon < ullon) {
            return false;
        }
        if (ullon < ROOT_ULLON || lrlon > ROOT_LRLON || ullat > ROOT_ULLAT || lrlat < ROOT_LRLAT) {
            return false;
        }
        return true;
    }


}
